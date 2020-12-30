/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.player.statistic

import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.util.future._

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger
import scala.collection.concurrent
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.jdk.CollectionConverters._
import scala.util.chaining._
import scala.util.{Failure, Success}

class StatisticService(implicit
    logger: Logger,
    executionContext: ExecutionContext,
    repository: StatisticRepository,
    taskService: TaskService
) {
  private val _statistics: concurrent.Map[UUID, Statistics] =
    new ConcurrentHashMap[UUID, Statistics]().asScala

  private[statistic] def loadStatistics(playerId: UUID): Unit =
    Await.result(statisticsFuture(playerId), 1000.millis)
      .tap { _statistics.update(playerId, _) }

  private[statistic] def invalidateStatistics(playerId: UUID): Unit =
    _statistics.remove(playerId)

  def statistics(playerId: UUID): Statistics =
    statisticsFuture(playerId).getOrThrow

  // TODO should repositories always return a Future allowing the service to determine what methodology to use?
  // TODO this also allows SQLite repositories to work on the main thread regardless of using a Future
  def statisticsFuture(playerId: UUID): Future[Statistics] =
    _statistics.get(playerId) match {
      case Some(statistics) => Future.successful(statistics)
      case None             => Future { repository.load(playerId) }
      // don't update cache for offline players
    }

  private def updateStatistic(
      transform: Statistic => Statistic,
      deltaStatistics: Statistic*
  ): Future[Unit] = {
    deltaStatistics.foreach { it =>
      _statistics.updateWith(it.playerId) {
        case Some(statistics) =>
          val playerStatistic = statistics.all.get(it.statistic) match {
            case Some(statistic) => statistic
            case None            => Statistic(it.playerId, it.statistic)
          }
          val newStatistic = playerStatistic |> transform |> { it.statistic -> _ }
          new Statistics(statistics.all + newStatistic) |> Some.apply
        case None => None // don't update cache for offline players
      }
    }

    repository.save(deltaStatistics: _*)
  }

  /** Increases a player's statistic by amount. Statistics are permanent. For daily
    * or weekly statistics that need to be reset append the statistic's name with a
    * unique identifier.
    */
  def increaseStatistic(statistic: String, amount: Long, playerIds: UUID*): Unit = {
    val deltaStatistic = playerIds.map { Statistic(_, statistic, amount) }
    updateStatistic(_.increase(amount), deltaStatistic: _*).immediatelyOrOnComplete {
      case Success(_) => // TODO fire statistic events
      case Failure(exception) =>
        logger.severe {
          s"""Failed to increase $statistic by $amount for ${playerIds.size} players!
             |${exception.getMessage}
             |${playerIds.mkString("\n")}""".stripMargin
        }
    }
  }

  def increaseStatistics(
      statistics: List[String],
      amount: Long,
      playerIds: UUID*
  ): Unit = {
    val deltaStatistic = statistics.flatMap { statistic =>
      playerIds.map { Statistic(_, statistic, amount) }
    }
    updateStatistic(_.increase(amount), deltaStatistic: _*).immediatelyOrOnComplete {
      case Success(_) => // TODO fire statistic events
      case Failure(exception) =>
        logger.severe {
          s"""Failed to increase $statistics by $amount for ${playerIds.size} players!
             |${exception.getMessage}
             |${playerIds.mkString("\n")}""".stripMargin
        }
    }
  }
}
