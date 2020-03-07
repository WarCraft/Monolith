package gg.warcraft.monolith.bootstrap

import java.net.URLClassLoader

import io.circe.generic.auto._
import io.circe.parser._
import org.bukkit.command.{Command, CommandSender, ConsoleCommandSender}
import org.bukkit.plugin.java.JavaPlugin

class Plugin extends JavaPlugin {
  private final val CMD_RELOAD_MISSING_ARGS = ""
  private final val CMD_RELOAD_MALFORMED_ARGS = ""

  private var config: Config = _
  private var loaders: Map[String, URLClassLoader] = Map()
  private var instances: Map[String, Object] = Map()

  private def enable(plugin: String): Unit = {
    val hotreload = getDataFolder.toPath.resolve("hotreload")
    val jar = hotreload.toFile.listFiles((_, name) => name.startsWith(plugin))(0)
    val url = jar.toPath.toUri.toURL
    val loader = new URLClassLoader(Array(url), getClass.getClassLoader)
    loaders += (plugin -> loader)

    val main = s"gg.warcraft.$plugin.spigot.${plugin.capitalize}Plugin"
    val instance = loader.loadClass(main).newInstance()
    instance.getClass.getMethod("enable").invoke(instance)
    instances += (plugin -> instance)
  }

  private def disable(plugin: String): Unit = {
    val instance = instances(plugin)
    instance.getClass.getMethod("disable").invoke(instance)
    instances -= plugin

    loaders(plugin).close()
    loaders -= plugin
  }

  private def reload(plugin: String): Unit = {
    disable(plugin)
    enable(plugin)
  }

  private def enableAll(): Unit = config.plugins foreach enable

  private def disableAll(): Unit = config.plugins.reverse foreach disable

  private def reloadAll(): Unit = {
    disableAll()
    enableAll()
  }

  private def reloadConfig(): Unit = {
    super.reloadConfig()
    config = decode[Config](getConfig.saveToString()) match {
      case Left(err) =>
        getLogger severe s"Failed to parse config: ${err.getMessage}"
        Config(Nil)
      case Right(config) => config
    }
  }

  override def onLoad(): Unit = saveDefaultConfig()

  override def onEnable(): Unit = {
    reloadConfig()
    enableAll()
  }

  override def onCommand(
      sender: CommandSender,
      cmd: Command,
      label: String,
      args: Array[String]
  ): Boolean = onCommand(args.toList)(sender)

  private def onCommand(args: List[String])(
      implicit sender: CommandSender
  ): Boolean = args match {
    case "reload" :: tail if isConsole => onReloadCommand(tail); true
    case _                             => false
  }

  private def onReloadCommand(args: List[String])(
      implicit sender: CommandSender
  ): Unit = args match {
    case "--all" :: Nil | "-a" :: Nil     => reloadAll()
    case "--config" :: Nil | "-c" :: Nil  => reloadConfig()
    case it :: Nil if instances contains it => reload(it)
    case Nil                              => sender sendMessage CMD_RELOAD_MISSING_ARGS
    case _                                => sender sendMessage CMD_RELOAD_MALFORMED_ARGS
  }

  private implicit def isConsole(implicit sender: CommandSender): Boolean =
    sender.isInstanceOf[ConsoleCommandSender]
}
