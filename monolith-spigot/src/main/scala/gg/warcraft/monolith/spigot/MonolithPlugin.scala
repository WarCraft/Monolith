package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.api.core.{MonolithConfig, ServerShutdownEvent}
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.handler.{DailyTicker, DebuggingHandler}
import gg.warcraft.monolith.api.entity.data.EntityDataHandler
import gg.warcraft.monolith.api.entity.status.StatusHandler
import gg.warcraft.monolith.api.player.data.{PlayerDataHandler, PlayerDataUpdater}
import gg.warcraft.monolith.api.player.hiding.PlayerHidingHandler
import gg.warcraft.monolith.api.world.portal.PortalTicker
import gg.warcraft.monolith.spigot.combat.SpigotCombatEventMapper
import gg.warcraft.monolith.spigot.entity.SpigotEntityEventMapper
import gg.warcraft.monolith.spigot.item.SpigotItemEventMapper
import gg.warcraft.monolith.spigot.menu.SpigotMenuHandler
import gg.warcraft.monolith.spigot.player.SpigotPlayerEventMapper
import gg.warcraft.monolith.spigot.world.SpigotWorldEventMapper
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
    val config = parseConfig[MonolithConfig](getConfig.saveToString())
    blockBuildService.readConfig(config)
    blockBackupService.restoreBackups()

    enableHandlers()
    enableTasks()
    enableEventMappers()
  }

  override def onDisable(): Unit = {
    eventService.publish(new ServerShutdownEvent)
    super.onDisable()
  }

  private def enableEventMappers(): Unit = {
    subscribe(new SpigotCombatEventMapper)
    subscribe(new SpigotEntityEventMapper)
    subscribe(new SpigotPlayerEventMapper)
    subscribe(new SpigotItemEventMapper)
    subscribe(new SpigotMenuHandler)
    subscribe(new SpigotWorldEventMapper)
  }

  private def enableHandlers(): Unit = {
    eventService.subscribe(new DebuggingHandler)
    eventService.subscribe(new EntityDataHandler)
    eventService.subscribe(new StatusHandler)
    eventService.subscribe(new PlayerDataHandler)
    eventService.subscribe(new PlayerHidingHandler)

    /* TODO
     AttributesInitializationHandler attributesInitializationHandler =
                injector.getInstance(AttributesInitializationHandler.class);
        eventService.subscribe(attributesInitializationHandler);
     */
  }

  private def enableTasks(): Unit = {
    taskService.runTask(1.seconds, new DailyTicker().run)
    taskService.runTask(1.ticks, new PlayerDataUpdater().run)
    taskService.runTask(4.ticks, new PortalTicker().run)
  }
}
