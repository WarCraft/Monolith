package gg.warcraft.monolith.spigot.bootstrap

import io.circe.yaml.parser
import org.bukkit.command.{Command, CommandSender, ConsoleCommandSender}
import org.bukkit.event.{EventHandler, EventPriority, HandlerList, Listener}
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class BootstrapPlugin extends JavaPlugin with Listener {
  private final val MONOLITH = "monolith"
  private final val CMD_RELOAD_MISSING_ARGS = ""
  private final val CMD_RELOAD_MALFORMED_ARGS = ""

  private val manager = getServer.getPluginManager

  private var config: List[String] = Nil

  private def enable(plugin: String): Unit = {
    val libs = getDataFolder.toPath.resolve("lib") // TODO fix
    val jar = libs.toFile.listFiles((_, name) => name.startsWith(plugin))(0)
    manager.loadPlugin(jar)
  }

  private def disable(plugin: String): Unit = {
    val instance = manager.getPlugin(plugin)
    manager.disablePlugin(instance, true)
  }

  private def enableAll(): Unit = {
    enable(MONOLITH)
    config foreach enable
  }

  private def disableAll(): Unit = {
    config.reverse foreach disable
    disable(MONOLITH)
  }

  private def reload(plugin: String): Unit = {
    disable(plugin)
    enable(plugin)
  }

  private def reloadAll(): Unit = {
    disableAll()
    reloadConfig()
    enableAll()
  }

  override def reloadConfig(): Unit = {
    super.reloadConfig()
    config = parser.parse(getConfig.saveToString()) match {
      case Left(err) => getLogger severe err.getMessage; Nil
      case Right(json) =>
        json.as[List[String]] match {
          case Left(err)     => getLogger severe err.getMessage; Nil
          case Right(config) => config
        }
    }
  }

  override def onLoad(): Unit = {
    saveDefaultConfig()
    getServer.getPluginManager.registerEvents(this, this)
    new BukkitRunnable {
      override def run(): Unit =
        HandlerList.unregisterAll(this.asInstanceOf[Listener])
    }.runTaskLater(this, 1)
  }

  override def onEnable(): Unit = {
    reloadConfig()
    enableAll() // TODO is this necessary on startup?
  }

  override def onCommand(
      sender: CommandSender,
      cmd: Command,
      label: String,
      args: Array[String]
  ): Boolean = {
    implicit val implicitSender: CommandSender = sender
    val isConsole = sender.isInstanceOf[ConsoleCommandSender]
    args.toList match {
      case "reload" :: tail if isConsole => onReloadCommand(tail); true
      case _                             => false
    }
  }

  private def onReloadCommand(args: List[String])(
      implicit sender: CommandSender
  ): Unit = args match {
    case "--all" :: Nil | "-a" :: Nil    => reloadAll()
    case it :: Nil if config contains it => reload(it)
    case Nil                             => sender sendMessage CMD_RELOAD_MISSING_ARGS
    case _                               => sender sendMessage CMD_RELOAD_MALFORMED_ARGS
  }

  @EventHandler(priority = EventPriority.LOWEST)
  def onPlayerPreConnect(event: AsyncPlayerPreLoginEvent): Unit = {
    event disallow (AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "The server is still starting...")
  }
}
