package gg.warcraft.monolith.api.player.statistic

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.task.TaskService
import io.getquill.context.jdbc.JdbcContext

import scala.collection.concurrent.{Map => ConcurrentMap}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

class StatisticService(
    implicit database: JdbcContext[_, _],
    logger: Logger,
    taskService: TaskService
) {
  private final implicit val context: ExecutionContext = ExecutionContext.global

  private val statistics: ConcurrentMap[UUID, Statistics] =
    new ConcurrentHashMap[UUID, Statistics]().asScala

  import database._

  private[statistic] def loadStatistics(playerId: UUID): Unit =
    Await
      .result(getStatistics(playerId), 1000.millis)
      .tap { statistics update (playerId, _) }

  private[statistic] def invalidateStatistics(playerId: UUID): Unit =
    statistics remove playerId

  def getStatistics(playerId: UUID): Future[Statistics] = {
    statistics get playerId match {
      case Some(playerStatistics) =>
        Future.successful(playerStatistics)
      case None =>
        Future {
          database
            .run { query[Statistic] filter { _.playerId == lift(playerId) } }
            .iterator
            .map(it => it.statistic -> it)
            .toMap
            .pipe { Statistics.apply }
        }
    }
  }

  private def updateStatistics(
      playerId: UUID,
      currencies: Map[String, Statistic],
      amount: Int,
      currency: String*
  ): Map[String, Statistic] = currency.map { it =>
    it -> (currencies get it match {
      case Some(it) => it increase amount
      case None     => Statistic(playerId, it) increase amount
    })
  }.toMap

  /** Increases a player's statistic by amount. Statistics are permanent, for daily
    * or weekly statistics that need to be reset append the statistic's name with a
    * unique identifier. */
  def increaseStatistic(playerId: UUID, amount: Int, statistic: String*): Unit = {
    var updated: Map[String, Statistic] = Map.empty

    statistics.updateWith(playerId) {
      case Some(statistics) =>
        val current = statistics.statistics
        updated = updateStatistics(playerId, current, amount, statistic: _*)
        statistics
          .copy(statistics = statistics.statistics ++ updated)
          .pipe { Some.apply }
      case None =>
        updated = updateStatistics(playerId, Map.empty, amount, statistic: _*)
        None
    }

    Future {
      database run {
        liftQuery(statistic.toList) foreach { statistic =>
          query[Statistic]
            .filter(it => it.playerId == lift(playerId) && it.statistic == statistic)
            .insert(updated(statistic))
            .onConflictUpdate(_.playerId, _.statistic)((it, _) =>
              it.value -> (it.value + lift(amount))
            )
        }
      }
    }
  }
}
