package gg.warcraft.monolith.spigot

import java.util.logging.Logger

import gg.warcraft.monolith.api.core.MonolithPlugin
import gg.warcraft.monolith.api.core.event.EventService
import org.bukkit.event.{HandlerList, Listener}
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Server

class SpigotMonolithPlugin extends JavaPlugin with MonolithPlugin {
  protected implicit val implicitServer: Server = getServer
  protected implicit val implicitLogger: Logger = getLogger

  protected implicit val eventService: EventService =
    new PluginEventService(Implicits.eventService)

  override def onLoad(): Unit = saveDefaultConfig()

  override def onDisable(): Unit = {
    HandlerList unregisterAll this
    eventService.asInstanceOf[PluginEventService].unsubscribeAll()
    databases foreach { _.close() }
  }

  protected def subscribe(listener: Listener): Unit =
    getServer.getPluginManager registerEvents (listener, this)

  protected def unsubscribe(listener: Listener): Unit =
    HandlerList unregisterAll listener
}
