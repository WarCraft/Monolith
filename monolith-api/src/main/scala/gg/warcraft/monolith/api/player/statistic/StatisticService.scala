package gg.warcraft.monolith.api.player.statistic

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.util.future._
import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

import scala.collection.concurrent.{Map => ConcurrentMap}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success}
import scala.util.chaining._

class StatisticService(
    implicit database: JdbcContext[SqliteDialect, SnakeCase],
    logger: Logger,
    taskService: TaskService
) {
  import database._

  private implicit val executionContext: ExecutionContext =
    ExecutionContext.global
  private implicit val statisticInsertMeta: InsertMeta[Statistic] =
    insertMeta[Statistic]()

  private val statistics: ConcurrentMap[UUID, Statistics] =
    new ConcurrentHashMap[UUID, Statistics]().asScala

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
            .map { it => it.statistic -> it }
            .toMap
            .pipe { new Statistics(_) }
        }
    }
  }

  private def updateStatistic(
      f: Statistic => Statistic,
      deltaStatistic: Statistic*
  ): Future[Unit] = {
    deltaStatistic foreach { s =>
      statistics.updateWith(s.playerId) {
        case Some(statistics) =>
          val playerStatistic = statistics.all get s.statistic match {
            case Some(statistic) => statistic
            case None            => Statistic(s.playerId, s.statistic)
          }
          val updatedStatistic = playerStatistic |> f |> { s.statistic -> _ }
          new Statistics(statistics.all + updatedStatistic) |> Some.apply
        case None => None // don't update cache for offline players
      }
    }

    Future {
      deltaStatistic foreach { s =>
        database run {
          query[Statistic]
            .insert { lift(s) }
            .onConflictUpdate(_.playerId, _.statistic)((_1, _2) =>
              _1.value -> (_1.value + _2.value)
            )
        }
      }
    }
  }

  /** Increases a player's statistic by amount. Statistics are permanent, for daily
    * or weekly statistics that need to be reset append the statistic's name with a
    * unique identifier. */
  def increaseStatistic(statistic: String, amount: Long, playerId: UUID*): Unit = {
    val deltaStatistics = playerId map { Statistic(_, statistic, amount) }
    updateStatistic(_ increase amount, deltaStatistics: _*) immediatelyOrOnComplete {
      case Success(_) =>
      // TODO fire statistic events
      case Failure(exception) =>
        logger severe {
          s"""Failed to increase $statistic by $amount for $playerId
             |${exception.getMessage}""".stripMargin
        }
    }
  }
}
// TODO split the immediateOrOnComplete calls for each player
