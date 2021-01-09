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

import gg.warcraft.monolith.api.core.data.ServerDataService
import gg.warcraft.monolith.api.entity.team.TeamService
import gg.warcraft.monolith.api.player.statistic.StatisticService
import gg.warcraft.monolith.api.util.chaining._

import java.time.ZoneOffset
import java.util.UUID
import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.chaining._

class PlayerDataService(implicit
    logger: Logger,
    repository: PlayerDataRepository,
    statisticService: StatisticService,
    serverDataService: ServerDataService,
    teamService: TeamService
) {
  private var _data: Map[UUID, PlayerData] = Map.empty

  def data: Map[UUID, PlayerData] = _data
  def dataFuture(id: UUID): Future[PlayerData] =
    _data.get(id) match {
      case Some(data) => data |> Future.successful
      case None =>
        Future {
          repository.load(id) match {
            case Some(playerData) => playerData
            case None             => null
          }
        }
    }

  private[player] def setPlayerData(data: PlayerData): Unit = {
    _data += (data.id -> data)
    repository.save(data)
    // TODO if team has changed fire event, or move to playerservice?
  }

  private[data] def updatePlayerData(id: UUID): Unit = {
    val data = _data(id)
    val oldLastSeen = data.timeLastSeen
    val newLastSeen = serverDataService.serverTime

    val timePlayed = newLastSeen.toEpochSecond(ZoneOffset.UTC) -
      oldLastSeen.toEpochSecond(ZoneOffset.UTC)
    statisticService.increaseStatistic("TimePlayed", timePlayed, id)
    setPlayerData(data.copy(timeLastSeen = newLastSeen))
  }

  private[data] def loadPlayerData(id: UUID): Option[PlayerData] =
    repository.load(id).tap {
      case Some(data) => _data += (data.id -> data)
      case None       =>
    }

  private[data] def invalidatePlayerData(id: UUID): Unit =
    _data -= id
}
