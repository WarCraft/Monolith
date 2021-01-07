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

import gg.warcraft.monolith.api.block.backup.{
  BlockBackupRepository, PostgresBlockBackupRepository,
  RestoreAllBlockBackupsCommand, SqliteBlockBackupRepository
}
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.auth.AuthModeHandler
import gg.warcraft.monolith.api.core.auth.command.{
  BuildModeCommand, DebugModeCommand, ModModeCommand
}
import gg.warcraft.monolith.api.core.data.{
  PostgresServerDataRepository, ServerDataRepository, SqliteServerDataRepository
}
import gg.warcraft.monolith.api.core.handler.{DailyTicker, DebuggingHandler}
import gg.warcraft.monolith.api.core.{
  DatabaseConfig, MonolithConfig, ServerShutdownEvent
}
import gg.warcraft.monolith.api.entity.data.{
  EntityDataCacheHandler, EntityDataRepository, PostgresEntityDataRepository,
  SqliteEntityDataRepository
}
import gg.warcraft.monolith.api.entity.status.StatusHandler
import gg.warcraft.monolith.api.entity.team.TeamStaffCommand
import gg.warcraft.monolith.api.player.currency.{
  CurrencyCacheHandler, CurrencyRepository, PostgresCurrencyRepository,
  SqliteCurrencyRepository
}
import gg.warcraft.monolith.api.player.data._
import gg.warcraft.monolith.api.player.hiding.PlayerHidingHandler
import gg.warcraft.monolith.api.player.statistic.archive.{
  PostgresStatisticArchiveRepository, SqliteStatisticArchiveRepository,
  StatisticArchiveRepository
}
import gg.warcraft.monolith.api.player.statistic.{
  PostgresStatisticRepository, SqliteStatisticRepository, StatisticCacheHandler,
  StatisticRepository
}
import gg.warcraft.monolith.api.world.portal.PortalTicker
import gg.warcraft.monolith.spigot.block.SpigotBlockEventMapper
import gg.warcraft.monolith.spigot.combat.SpigotCombatEventMapper
import gg.warcraft.monolith.spigot.core.SpigotBaseHealthHandler
import gg.warcraft.monolith.spigot.core.command.SpigotCommandEventMapper
import gg.warcraft.monolith.spigot.entity.SpigotEntityEventMapper
import gg.warcraft.monolith.spigot.item.SpigotItemEventMapper
import gg.warcraft.monolith.spigot.menu.SpigotMenuHandler
import gg.warcraft.monolith.spigot.player.SpigotPlayerEventMapper
import gg.warcraft.monolith.spigot.world.SpigotWorldEventMapper
import io.circe.generic.auto._

class MonolithPlugin extends SpigotMonolithPlugin {
  import implicits._

  override def onLoad(): Unit = {
    super.onLoad()
    implicits.init()
  }

  override def onEnable(): Unit = {
    import gg.warcraft.monolith.api.util.codecs.circe._
    import gg.warcraft.monolith.api.util.codecs.monolith.{
      colorCodeDecoder, directionDecoder, particleColorDecoder, worldDecoder
    }
    val config = parseConfig[MonolithConfig](getConfig.saveToString())

    upgradeDatabase(config.database, getDataFolder, getClassLoader)
    val repositories = configureRepositories(config.database)
    implicits.configure(config, repositories)

    blockBuildService.readConfig(config)
    serverDataService.readConfig(config)
    blockBackupService.restoreAllBackups()

    enableHandlers()
    enableCommands()
    enableTasks()
    enableEventMappers(config)
  }

  override def onDisable(): Unit = {
    eventService.publish(new ServerShutdownEvent)
    super.onDisable()
  }

  private def configureRepositories(config: DatabaseConfig): (
      ServerDataRepository,
      EntityDataRepository,
      PlayerDataRepository,
      CurrencyRepository,
      StatisticRepository,
      StatisticArchiveRepository,
      BlockBackupRepository
  ) =
    if (config.embedded) {
      val sqliteConfig = parseDatabaseConfig(config, getDataFolder)
      (
        new SqliteServerDataRepository(sqliteConfig),
        new SqliteEntityDataRepository(sqliteConfig),
        new SqlitePlayerDataRepository(sqliteConfig),
        new SqliteCurrencyRepository(sqliteConfig),
        new SqliteStatisticRepository(sqliteConfig),
        new SqliteStatisticArchiveRepository(sqliteConfig),
        new SqliteBlockBackupRepository(sqliteConfig)
      )
    } else {
      val postgresConfig = parseDatabaseConfig(config, getDataFolder)
      (
        new PostgresServerDataRepository(postgresConfig),
        new PostgresEntityDataRepository(postgresConfig),
        new PostgresPlayerDataRepository(postgresConfig),
        new PostgresCurrencyRepository(postgresConfig),
        new PostgresStatisticRepository(postgresConfig),
        new PostgresStatisticArchiveRepository(postgresConfig),
        new PostgresBlockBackupRepository(postgresConfig)
      )
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
      new RestoreAllBlockBackupsCommand,
      new RestoreAllBlockBackupsCommand.Handler
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
    val dailyTicker = new DailyTicker()
    taskService.runTask(1.seconds, () => dailyTicker.run())

    val playerDataTicker = new PlayerDataTicker()
    taskService.runTask(1.seconds, () => playerDataTicker.run())

    val portalTicker = new PortalTicker()
    taskService.runTask(4.ticks, () => portalTicker.run())
  }
}
