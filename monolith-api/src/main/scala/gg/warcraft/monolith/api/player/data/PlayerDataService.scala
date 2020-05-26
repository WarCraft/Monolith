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

package gg.warcraft.monolith.api.player.data

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.Codecs
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.player.statistic.StatisticService
import gg.warcraft.monolith.api.util.chaining._
import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.{ExecutionContext, Future}
import scala.util.chaining._

class PlayerDataService(implicit
    logger: Logger,
    context: ExecutionContext,
    database: JdbcContext[SqliteDialect, SnakeCase],
    statisticService: StatisticService,
    teamService: TeamService
) {
  import database._

  private implicit val teamDecoder: MappedEncoding[String, Option[Team]] =
    Codecs.Quill.teamDecoder
  private implicit val teamEncoder: MappedEncoding[Team, String] =
    Codecs.Quill.teamEncoder

  private var _data: Map[UUID, PlayerData] = Map.empty

  def getPlayerData(id: UUID): Future[PlayerData] =
    _data.get(id) match {
      case Some(data) => data |> Future.successful
      case None =>
        Future {
          database.run {
            query[PlayerData] filter { _.id == lift(id) }
          }.headOption match {
            case Some(playerData) => playerData.asInstanceOf[PlayerData]
            case None             => PlayerData(id)
          }
        }
    }

  // TODO change access level
  private[monolith] def setPlayerData(data: PlayerData): Unit = {
    _data += (data.id -> data)
    Future {
      database.run {
        query[PlayerData]
          .insert { lift(data) }
          .onConflictUpdate(_.id)(
            (t, e) => t.team -> e.team,
            (t, e) => t.timeConnected -> e.timeConnected,
            (t, e) => t.timeLastSeen -> e.timeLastSeen
          )
      }
    }
    // TODO if team has changed fire event, or move to playerservice?
  }

  private[data] def updatePlayerData(id: UUID): Unit = {
    val data = _data(id)
    val oldLastSeen = data.timeLastSeen
    val newLastSeen = System.currentTimeMillis
    statisticService.increaseStatistic("TimePlayed", newLastSeen - oldLastSeen, id)
    setPlayerData(data.copy(timeLastSeen = newLastSeen))
  }

  private[data] def loadPlayerData(id: UUID): Option[PlayerData] =
    database.run { query[PlayerData].filter { _.id == lift(id) } }
      .headOption
      .tap {
        case Some(data) => _data += (data.id -> data)
        case None       =>
      }

  private[data] def invalidatePlayerData(id: UUID): Unit =
    _data -= id
}
