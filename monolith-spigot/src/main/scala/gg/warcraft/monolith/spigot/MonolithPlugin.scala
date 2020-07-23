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

package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.api.block.backup.BlockBackupCommand
import gg.warcraft.monolith.api.core.{ColorCode, MonolithConfig, ServerShutdownEvent}
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.handler.{DailyTicker, DebuggingHandler}
import gg.warcraft.monolith.api.core.Codecs.Circe._
import gg.warcraft.monolith.api.core.auth.command.{
  BuildModeCommand, DebugModeCommand, ModModeCommand
}
import gg.warcraft.monolith.api.core.auth.AuthModeHandler
import gg.warcraft.monolith.api.core.types.DatabaseContext
import gg.warcraft.monolith.api.entity.data.EntityDataCacheHandler
import gg.warcraft.monolith.api.entity.status.StatusHandler
import gg.warcraft.monolith.api.entity.team.TeamStaffCommand
import gg.warcraft.monolith.api.item.ItemType
import gg.warcraft.monolith.api.player.currency.CurrencyCacheHandler
import gg.warcraft.monolith.api.player.data.{
  PlayerDataCacheHandler, PlayerDataTicker
}
import gg.warcraft.monolith.api.player.hiding.PlayerHidingHandler
import gg.warcraft.monolith.api.player.statistic.StatisticCacheHandler
import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.portal.PortalTicker
import gg.warcraft.monolith.spigot.block.SpigotBlockEventMapper
import gg.warcraft.monolith.spigot.combat.SpigotCombatEventMapper
import gg.warcraft.monolith.spigot.core.command.SpigotCommandEventMapper
import gg.warcraft.monolith.spigot.core.SpigotBaseHealthHandler
import gg.warcraft.monolith.spigot.entity.SpigotEntityEventMapper
import gg.warcraft.monolith.spigot.item.SpigotItemEventMapper
import gg.warcraft.monolith.spigot.menu.SpigotMenuHandler
import gg.warcraft.monolith.spigot.player.SpigotPlayerEventMapper
import gg.warcraft.monolith.spigot.world.SpigotWorldEventMapper
import io.circe.Decoder
import io.circe.generic.auto._
import io.getquill.{SnakeCase, SqliteDialect}

class MonolithPlugin extends SpigotMonolithPlugin {
  import implicits._

  override def onLoad(): Unit = {
    super.onLoad()

    implicit val databaseContext: DatabaseContext =
      initDatabase(SqliteDialect, SnakeCase, getDataFolder)
    upgradeDatabase(getDataFolder, getClassLoader)

    implicits.init()
  }

  override def onEnable(): Unit = {
    implicit val colorCodeDec: Decoder[ColorCode] = enumDecoder(ColorCode.valueOf)
    implicit val itemTypeDec: Decoder[ItemType] = enumDecoder(ItemType.valueOf)
    implicit val worldDec: Decoder[World] = worldDecoder
    val config = parseConfig[MonolithConfig](getConfig.saveToString())

    implicits.configure(config)

    blockBuildService.readConfig(config)
    serverDataService.readConfig(config)
    blockBackupService.restoreBackups()

    enableHandlers()
    enableCommands()
    enableTasks()
    enableEventMappers(config)
  }

  override def onDisable(): Unit = {
    eventService.publish(new ServerShutdownEvent)
    super.onDisable()
  }

  private def enableCommands(): Unit = {
    // Auth
    commandService.registerCommand(
      new BuildModeCommand,
      new BuildModeCommand.Handler
    )
    commandService.registerCommand(
      new ModModeCommand,
      new ModModeCommand.Handler
    )
    commandService.registerCommand(
      new DebugModeCommand,
      new DebugModeCommand.Handler
    )

    // Block
    commandService.registerCommand(
      new BlockBackupCommand,
      new BlockBackupCommand.Handler
    )

    // Entity
    commandService.registerCommand(
      new TeamStaffCommand,
      new TeamStaffCommand.Handler
    )
  }

  private def enableEventMappers(config: MonolithConfig): Unit = {
    subscribe(new SpigotBaseHealthHandler(config))
    subscribe(new SpigotBlockEventMapper)
    subscribe(new SpigotCommandEventMapper)
    subscribe(new SpigotCombatEventMapper)
    subscribe(new SpigotEntityEventMapper)
    subscribe(new SpigotPlayerEventMapper)
    subscribe(new SpigotItemEventMapper)
    subscribe(new SpigotWorldEventMapper)

    subscribe(new SpigotMenuHandler)
  }

  private def enableHandlers(): Unit = {
    eventService.subscribe(new AuthModeHandler)
    eventService.subscribe(new DebuggingHandler)
    eventService.subscribe(new EntityDataCacheHandler)
    eventService.subscribe(new CurrencyCacheHandler)
    eventService.subscribe(new StatisticCacheHandler)
    eventService.subscribe(new StatusHandler)
    eventService.subscribe(new PlayerDataCacheHandler)
    eventService.subscribe(new PlayerHidingHandler)
  }

  private def enableTasks(): Unit = {
    taskService.runTask(1.seconds, new DailyTicker().run)
    taskService.runTask(1.seconds, new PlayerDataTicker().run)
    taskService.runTask(4.ticks, new PortalTicker().run)
  }
}
