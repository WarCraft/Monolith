package gg.warcraft.monolith.bootstrap

import java.net.URLClassLoader

import gg.warcraft.monolith.MonolithPlugin
import io.circe.yaml.parser
import org.bukkit.command.{Command, CommandSender, ConsoleCommandSender}
import org.bukkit.plugin.java.JavaPlugin

class BootstrapPlugin extends JavaPlugin {
  private final val CMD_RELOAD_MISSING_ARGS = ""
  private final val CMD_RELOAD_MALFORMED_ARGS = ""

  private var config: List[String] = Nil
  private var loaders: Map[String, URLClassLoader] = Map()
  private var instances: Map[String, MonolithPlugin] = Map()

  private def enable(plugin: String): Unit = {
    val libs = getDataFolder.toPath.resolve("lib")
    val jar = libs.toFile.listFiles((_, name) => name.startsWith(plugin))(0)
    val url = jar.toPath.toUri.toURL
    val loader = new URLClassLoader(Array(url), getClass.getClassLoader)
    loaders += (plugin -> loader)

    val main = s"gg.warcraft.$plugin.spigot.${plugin.capitalize}Plugin"
    val instance = loader.loadClass(main).newInstance().asInstanceOf[MonolithPlugin]
    instance.enable()
    instances += (plugin -> instance)
  }

  private def disable(plugin: String): Unit = {
    instances(plugin).disable()
    instances -= plugin

    loaders(plugin).close()
    loaders -= plugin
  }

  private def enableAll(): Unit = config foreach enable
  private def disableAll(): Unit = config.reverse foreach disable

  private def reload(plugin: String): Unit = { disable(plugin); enable(plugin) }
  private def reloadAll(): Unit = { disableAll(); enableAll() }

  private def reloadConfig(): Unit = {
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

  override def onLoad(): Unit = saveDefaultConfig()
  override def onEnable(): Unit = { reloadConfig(); enableAll() }

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
    case "--all" :: Nil | "-a" :: Nil       => reloadAll()
    case "--config" :: Nil | "-c" :: Nil    => reloadConfig()
    case it :: Nil if instances contains it => reload(it)
    case Nil                                => sender sendMessage CMD_RELOAD_MISSING_ARGS
    case _                                  => sender sendMessage CMD_RELOAD_MALFORMED_ARGS
  }

  private implicit def isConsole(implicit sender: CommandSender): Boolean =
    sender.isInstanceOf[ConsoleCommandSender]
}
