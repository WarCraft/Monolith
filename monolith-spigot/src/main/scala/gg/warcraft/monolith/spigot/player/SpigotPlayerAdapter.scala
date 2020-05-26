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

package gg.warcraft.monolith.spigot.player

import java.util.logging.Logger

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.entity.attribute.AttributeService
import gg.warcraft.monolith.api.entity.data.EntityDataService
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.entity.Entity
import gg.warcraft.monolith.api.entity.Entity.Type
import gg.warcraft.monolith.api.item.Inventory
import gg.warcraft.monolith.api.player.Player
import gg.warcraft.monolith.api.player.Player.Mode
import gg.warcraft.monolith.api.player.currency.{Currencies, CurrencyService}
import gg.warcraft.monolith.api.player.data.{PlayerData, PlayerDataService}
import gg.warcraft.monolith.api.player.statistic.{Statistics, StatisticService}
import gg.warcraft.monolith.api.util.future._
import gg.warcraft.monolith.spigot.entity.{
  SpigotEntityAdapter, SpigotEntityTypeMapper
}
import gg.warcraft.monolith.spigot.item.{
  SpigotInventory, SpigotItemMapper, SpigotItemTypeMapper, SpigotItemVariantMapper
}
import gg.warcraft.monolith.spigot.math.{SpigotAABBfMapper, SpigotVectorMapper}
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper

class SpigotPlayerAdapter(player: SpigotPlayer)(implicit
    logger: Logger,
    taskService: TaskService,
    attributeService: AttributeService,
    currencyService: CurrencyService,
    statisticService: StatisticService,
    statusService: StatusService,
    teamService: TeamService,
    entityDataService: EntityDataService,
    dataService: PlayerDataService,
    playerService: SpigotPlayerService,
    vectorMapper: SpigotVectorMapper,
    boundingBoxMapper: SpigotAABBfMapper,
    itemMapper: SpigotItemMapper,
    entityTypeMapper: SpigotEntityTypeMapper,
    gameModeMapper: SpigotGameModeMapper,
    locationMapper: SpigotLocationMapper,
    itemTypeMapper: SpigotItemTypeMapper,
    itemVariantMapper: SpigotItemVariantMapper
) extends SpigotEntityAdapter(player)
    with Player {
  override lazy val typed: Type = Entity.Type.PLAYER
  override lazy val inventory: Inventory = new SpigotInventory(player.getInventory)

  override def team: Option[Team] = data.team
  override def data: PlayerData =
    dataService.getPlayerData(id).getOrThrow
  override def currencies: Currencies =
    currencyService.currenciesFuture(id).getOrThrow
  override def statistics: Statistics =
    statisticService.statisticsFuture(id).getOrThrow

  override def mode: Mode = gameModeMapper.map(player.getGameMode)
  override def isOnline: Boolean = player.isOnline
  override def isSneaking: Boolean = player.isSneaking
  override def hasPermission(perm: String): Boolean = player.hasPermission(perm)

  override def sendMessage(message: Message): Unit =
    playerService.sendMessage(id, message)
  override def sendTitle(title: String, subTitle: String): Unit =
    playerService.sendTitle(id, title, subTitle)
}
