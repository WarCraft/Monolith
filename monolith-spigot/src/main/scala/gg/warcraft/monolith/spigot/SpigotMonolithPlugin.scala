package gg.warcraft.monolith.spigot

import java.util.logging.Logger

import gg.warcraft.monolith.api.core.MonolithPlugin
import gg.warcraft.monolith.api.core.event.EventService
import org.bukkit.event.{HandlerList, Listener}
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Server
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.plugin.Plugin

abstract class SpigotMonolithPlugin extends JavaPlugin with MonolithPlugin {
  protected implicit val server: Server = getServer
  protected implicit val plugin: Plugin = this
  protected implicit val logger: Logger = getLogger

  protected implicit val eventService: EventService =
    new EventService.Wrapper(Implicits.eventService)

  override def onLoad(): Unit = saveDefaultConfig()

  override def onDisable(): Unit = {
    HandlerList unregisterAll this
    eventService.asInstanceOf[EventService.Wrapper].unsubscribeAll()
    databases foreach { _.close() }
  }

  override def onCommand(
      sender: CommandSender,
      command: Command,
      label: String,
      args: Array[String]
  ): Boolean = {
    // TODO call command service
    false
  }

  protected def subscribe(listener: Listener): Unit =
    getServer.getPluginManager registerEvents (listener, this)

  protected def unsubscribe(listener: Listener): Unit =
    HandlerList unregisterAll listener
}
