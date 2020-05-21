package gg.warcraft.monolith.api.player.currency

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.core.types.DatabaseContext
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.util.future._

import scala.collection.concurrent
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success}
import scala.util.chaining._

class CurrencyService(implicit
    logger: Logger,
    executionContext: ExecutionContext,
    database: DatabaseContext,
    eventService: EventService,
    taskService: TaskService
) {
  import database._

  private val _currencies: concurrent.Map[UUID, Currencies] =
    new ConcurrentHashMap[UUID, Currencies]().asScala

  private[currency] def loadCurrencies(playerId: UUID): Unit =
    Await.result(currenciesFuture(playerId), 1000.millis)
      .tap { _currencies.update(playerId, _) }

  private[currency] def invalidateCurrencies(playerId: UUID): Unit =
    _currencies.remove(playerId)

  def currencies(playerId: UUID): Currencies =
    currenciesFuture(playerId).getOrThrow

  def currenciesFuture(playerId: UUID): Future[Currencies] =
    _currencies.get(playerId) match {
      case Some(currencies) => Future.successful(currencies)
      case None =>
        Future {
          database.run { query[Currency].filter { _.playerId == lift(playerId) } }
            .iterator.map { it => it.currency -> it }
            .toMap.pipe { new Currencies(_) }
          // don't update cache for offline players
        }
    }

  private def updateCurrency(
      transform: Currency => Currency,
      deltaCurrencies: Currency*
  ): Future[Unit] = {
    deltaCurrencies.foreach { it =>
      _currencies.updateWith(it.playerId) {
        case Some(currencies) =>
          val playerCurrency = currencies.all.get(it.currency) match {
            case Some(currency) => currency
            case None           => Currency(it.playerId, it.currency)
          }
          val newCurrency = playerCurrency |> transform |> { it.currency -> _ }
          new Currencies(currencies.all + newCurrency) |> Some.apply
        case None => None // don't update cache for offline players
      }
    }

    Future {
      database.run {
        liftQuery(deltaCurrencies).foreach { currency =>
          query[Currency]
            .insert { currency }
            .onConflictUpdate(_.playerId, _.currency)(
              (t, e) => t.amount -> (t.amount + e.amount),
              (t, e) => t.lifetime -> (t.lifetime + e.lifetime)
            )
        }
      }
    }
  }

  /** Adds an amount of currency to the player. This increments both their currency
    * as well as their lifetime currency. */
  def addCurrency(currency: String, amount: Int, playerIds: UUID*): Unit = {
    val deltaCurrencies = playerIds.map { Currency(_, currency, amount, amount) }
    updateCurrency(_.add(amount), deltaCurrencies: _*).immediatelyOrOnComplete {
      case Success(_) => // TODO fire currency events
      case Failure(exception) =>
        logger.severe {
          s"""Failed to add $amount $currency to ${playerIds.size} players!
             |${exception.getMessage}
             |${playerIds.mkString("\n")}""".stripMargin
        }
    }
  }

  /** Removes an amount of currency from the player, but leaves the lifetime currency
    * in tact. Throws an IllegalArgumentException if the player does not have
    * sufficient funds. */
  def removeCurrency(currency: String, amount: Int, playerId: UUID): Unit = {
    _currencies(playerId) // ensure player is online, will throw otherwise
    val deltaCurrency = Currency(playerId, currency, -amount)
    updateCurrency(_.remove(amount), deltaCurrency).immediatelyOrOnComplete {
      case Success(_) => // TODO fire currency events
      case Failure(exception) =>
        logger.severe {
          s"""Failed to remove $amount $currency from $playerId!
             |${exception.getMessage}""".stripMargin
        }
    }
  }

  /** Revokes an amount of currency from the player without regard for whether the
    * player actually has sufficient funds while also removing lifetime currency
    * from the player. This is generally only useful to respond to chargebacks. */
  def revokeCurrency(currency: String, amount: Int, playerId: UUID): Unit = {
    val deltaCurrency = Currency(playerId, currency, -amount, -amount)
    updateCurrency(_.revoke(amount), deltaCurrency).immediatelyOrOnComplete {
      case Success(_) => // TODO fire currency events
      case Failure(exception) =>
        logger.severe {
          s"""Failed to revoke $amount $currency from $playerId!
             |${exception.getMessage}""".stripMargin
        }
    }
  }
}
