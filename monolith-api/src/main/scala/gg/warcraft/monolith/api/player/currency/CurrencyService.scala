package gg.warcraft.monolith.api.player.currency

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.util.Ops._
import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

import scala.collection.concurrent.{Map => ConcurrentMap}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success}
import scala.util.chaining._

class CurrencyService(
    implicit database: JdbcContext[SqliteDialect, SnakeCase],
    logger: Logger,
    taskService: TaskService
) {
  import database._

  private implicit val executionContext: ExecutionContext =
    ExecutionContext.global
  private implicit val currencyInsertMeta: InsertMeta[Currency] =
    insertMeta[Currency]()

  private val currencies: ConcurrentMap[UUID, Currencies] =
    new ConcurrentHashMap[UUID, Currencies]().asScala

  private[currency] def loadCurrencies(playerId: UUID): Unit =
    Await
      .result(getCurrencies(playerId), 1000.millis)
      .tap { currencies update (playerId, _) }

  private[currency] def invalidateCurrencies(playerId: UUID): Unit =
    currencies remove playerId

  def getCurrencies(playerId: UUID): Future[Currencies] = {
    currencies get playerId match {
      case Some(playerCurrencies) =>
        Future.successful(playerCurrencies)
      case None =>
        Future {
          database
            .run { query[Currency] filter { _.playerId == lift(playerId) } }
            .iterator
            .map { it => it.currency -> it }
            .toMap
            .pipe { new Currencies(_) }
        }
    }
  }

  private def updateCurrency(
      f: Currency => Currency,
      deltaCurrency: Currency*
  ): Future[Unit] = {
    deltaCurrency foreach { c =>
      currencies.updateWith(c.playerId) {
        case Some(currencies) =>
          val playerCurrency = currencies.all get c.currency match {
            case Some(currency) => currency
            case None           => Currency(c.playerId, c.currency)
          }
          val updatedCurrency = playerCurrency |> f |> { c.currency -> _ }
          new Currencies(currencies.all + updatedCurrency) |> Some.apply
        case None => None // don't update cache for offline players
      }
    }

    Future {
      deltaCurrency foreach { c =>
        database run {
          query[Currency]
            .insert { lift(c) }
            .onConflictUpdate(_.playerId, _.currency)(
              (_1, _2) => _1.amount -> (_1.amount + _2.amount),
              (_1, _2) => _1.lifetime -> (_1.lifetime + _2.lifetime)
            )
        }
      }
    }
  }

  /** Adds an amount of currency to the player. This increments both their currency
    * as well as their lifetime currency. */
  def addCurrency(currency: String, amount: Int, playerId: UUID*): Unit = {
    val deltaCurrencies = playerId map { Currency(_, currency, amount, amount) }
    updateCurrency(_ add amount, deltaCurrencies: _*) immediatelyOrOnComplete {
      case Success(_) =>
      // TODO fire currency events
      case Failure(exception) =>
        logger severe {
          s"""Failure while adding $amount $currency to ${playerId.size} players
             |${exception.getMessage}""".stripMargin
        }
        playerId foreach { logger severe _.toString }
    }
  }

  /** Removes an amount of currency from the player, but leaves the lifetime currency
    * in tact. Throws an IllegalArgumentException if the player does not have
    * sufficient funds.*/
  def removeCurrency(currency: String, amount: Int, playerId: UUID): Unit = {
    currencies(playerId) // ensure player is online, will throw otherwise
    val deltaCurrency = Currency(playerId, currency, -amount)
    updateCurrency(_ remove amount, deltaCurrency) immediatelyOrOnComplete {
      case Success(_) =>
      // TODO fire currency events
      case Failure(exception) =>
        logger severe {
          s"""Failed to remove $amount $currency from $playerId
             |${exception.getMessage}""".stripMargin
        }
    }
  }

  /** Revokes an amount of currency from the player without regard for whether the
    * player actually has sufficient funds while also subtracting it from the
    * lifetime currency of the player */
  def revokeCurrency(currency: String, amount: Int, playerId: UUID): Unit = {
    val deltaCurrency = Currency(playerId, currency, -amount, -amount)
    updateCurrency(_ revoke amount, deltaCurrency) immediatelyOrOnComplete {
      case Success(_) =>
      // TODO fire currency events
      case Failure(exception) =>
        logger severe {
          s"""Failed to revoke $amount $currency from $playerId
             |${exception.getMessage}""".stripMargin
        }
    }
  }
}
