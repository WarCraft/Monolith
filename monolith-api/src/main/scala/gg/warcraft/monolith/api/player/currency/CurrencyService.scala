package gg.warcraft.monolith.api.player.currency

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.util.Ops._
import io.getquill.context.jdbc.JdbcContext

import scala.collection.concurrent.{Map => ConcurrentMap}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.jdk.CollectionConverters._
import scala.util.Failure
import scala.util.chaining._

class CurrencyService(
    implicit database: JdbcContext[_, _],
    logger: Logger,
    taskService: TaskService
) {
  private final implicit val context: ExecutionContext = ExecutionContext.global

  private val currencies: ConcurrentMap[UUID, Currencies] =
    new ConcurrentHashMap[UUID, Currencies]().asScala

  import database._

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
            .map(it => it.currency -> it)
            .toMap
            .pipe { Currencies.apply }
        }
    }
  }

  private def updateCurrencies(
      playerId: UUID,
      currencies: Map[String, Currency],
      f: Currency => Currency,
      currency: String*
  ): Map[String, Currency] = currency.map { it =>
    it -> (currencies get it match {
      case Some(it) => it |> f
      case None     => Currency(playerId, it) |> f
    })
  }.toMap

  private def updateCurrency(
      playerId: UUID,
      f: Currency => Currency,
      currency: String*
  ): Future[Unit] = {
    var current: Map[String, Currency] = Map.empty
    var updated: Map[String, Currency] = Map.empty

    currencies.updateWith(playerId) {
      case Some(currencies) =>
        current = currencies.currencies
        updated = updateCurrencies(playerId, current, f, currency: _*)
        currencies
          .copy(currencies = currencies.currencies ++ updated)
          .pipe { Some.apply }
      case None =>
        updated = updateCurrencies(playerId, Map.empty, f, currency: _*)
        None
    }

    Future {
      val diff: Map[String, (Int, Int)] = updated map {
        case (key, it) =>
          val amount = it.amount - current.get(key).map(_.amount).getOrElse(0)
          val lifetime = it.lifetime - current.get(key).map(_.lifetime).getOrElse(0)
          key -> (amount, lifetime)
      }

      database run {
        liftQuery(currency.toList) foreach { currency =>
          query[Currency]
            .filter(it => it.playerId == lift(playerId) && it.currency == currency)
            .insert(updated(currency))
            .onConflictUpdate(_.playerId, _.currency)(
              (it, _) => it.amount -> (it.amount + lift(diff(currency)._1)),
              (it, _) => it.lifetime -> (it.lifetime + lift(diff(currency)._2))
            )
        }
      }
    }
  }

  /** Adds an amount of currency to the player. This increments both their currency
    * as well as their lifetime currency. */
  def addCurrency(playerId: UUID, amount: Int, currency: String*): Unit = {
    updateCurrency(playerId, _ add amount, currency: _*) immediatelyOrOnComplete {
      case Failure(exception) =>
        logger severe {
          s"""Failed to add $amount ${currency mkString ", "} to $playerId
             |${exception.getMessage}""".stripMargin
        }
      case _ =>
    }
  }

  /** Removes an amount of currency from the player, but leaves the lifetime currency
    * in tact. Throws an IllegalStateException if the player does not have sufficient
    * funds.*/
  def removeCurrency(playerId: UUID, amount: Int, currency: String*): Unit = {
    updateCurrency(playerId, _ remove amount, currency: _*) immediatelyOrOnComplete {
      case Failure(exception) =>
        logger severe {
          s"""Failed to remove $amount ${currency mkString ", "} from $playerId
             |${exception.getMessage}""".stripMargin
        }
      case _ =>
    }
  }

  /** Revokes an amount of currency from the player without regard for whether the
    * player actually has sufficient funds while also subtracting it from the
    * lifetime currency of the player */
  def revokeCurrency(playerId: UUID, amount: Int, currency: String*): Unit = {
    updateCurrency(playerId, _ revoke amount, currency: _*) immediatelyOrOnComplete {
      case Failure(exception) =>
        logger severe {
          s"""Failed to revoke $amount ${currency mkString ", "} from $playerId
             |${exception.getMessage}""".stripMargin
        }
      case _ =>
    }
  }
}
