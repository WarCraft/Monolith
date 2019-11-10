package gg.warcraft.monolith.spigot

import org.bukkit.Server
import org.bukkit.plugin.Plugin

// TODO delete class when MonolithPlugin class is ported to scala
class ImplicitsJavaHack {
  def doTheThing(server: Server, plugin: Plugin): Unit = {
    Implicits.server = server
    Implicits.setPlugin(plugin)
    Implicits.setLogger(plugin.getLogger)
    Implicits.setDatabaseConfig(plugin.getDataFolder.getAbsolutePath)
  }
}
