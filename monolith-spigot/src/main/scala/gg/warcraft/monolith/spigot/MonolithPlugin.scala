package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.api.core.{MonolithConfig, ServerShutdownEvent}
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.handler.{DailyTicker, DebuggingHandler}
import gg.warcraft.monolith.api.entity.data.EntityDataHandler
import gg.warcraft.monolith.api.entity.status.StatusHandler
import gg.warcraft.monolith.api.player.data.{PlayerDataHandler, PlayerDataUpdater}
import gg.warcraft.monolith.api.player.hiding.PlayerHidingHandler
import gg.warcraft.monolith.api.util.Ops._
import gg.warcraft.monolith.api.world.portal.PortalTicker
import gg.warcraft.monolith.spigot.combat.SpigotCombatEventMapper
import gg.warcraft.monolith.spigot.entity.SpigotEntityEventMapper
import gg.warcraft.monolith.spigot.item.SpigotItemEventMapper
import gg.warcraft.monolith.spigot.menu.SpigotMenuHandler
import gg.warcraft.monolith.spigot.player.SpigotPlayerEventMapper
import gg.warcraft.monolith.spigot.world.SpigotWorldEventMapper
import io.getquill.{SnakeCase, SqliteDialect}

class MonolithPlugin extends SpigotMonolithPlugin {
  import gg.warcraft.monolith.spigot.Implicits._

  override def onEnable(): Unit = {
    Implicits.server = server
    Implicits.plugin = plugin
    Implicits.logger = logger

    initDatabase(SqliteDialect, SnakeCase, getDataFolder)
    upgradeDatabase(getDataFolder, getClassLoader)

    val config = parseConfig[MonolithConfig](getConfig.saveToString())
    blockBuildService.readConfig(config)
    blockBackupService.restoreBackups()

    enableHandlers()
    enableTasks()
    enableEventMappers()
  }

  override def onDisable(): Unit = {
    eventService publish new ServerShutdownEvent
    super.onDisable()
  }

  private def enableEventMappers(): Unit = getServer.getPluginManager |> { it =>
    it registerEvents (new SpigotCombatEventMapper, this)
    it registerEvents (new SpigotEntityEventMapper, this)
    it registerEvents (new SpigotPlayerEventMapper, this)
    it registerEvents (new SpigotItemEventMapper, this)
    it registerEvents (new SpigotMenuHandler(menuService), this)
    it registerEvents (new SpigotWorldEventMapper, this)
  }

  private def enableHandlers(): Unit = {
    val debuggingHandler =
      new DebuggingHandler(authService, entityService, playerService)
    eventService subscribe debuggingHandler
    eventService subscribe new EntityDataHandler(entityDataService)
    eventService subscribe new StatusHandler(statusService)
    eventService subscribe new PlayerDataHandler(playerDataService)
    eventService subscribe new PlayerHidingHandler(playerHidingService)

    /* TODO
     AttributesInitializationHandler attributesInitializationHandler =
                injector.getInstance(AttributesInitializationHandler.class);
        eventService.subscribe(attributesInitializationHandler);
   */
  }

  private def enableTasks(): Unit = {
    val dailyTicker = new DailyTicker()
    taskService runTask (1.seconds, dailyTicker.run)

    val playerDataUpdater = new PlayerDataUpdater(playerService, playerDataService)
    taskService runTask (1.ticks, playerDataUpdater.run)

    val portalTicker = new PortalTicker(portalService)
    taskService runTask (5.ticks, portalTicker.run)
  }
}
