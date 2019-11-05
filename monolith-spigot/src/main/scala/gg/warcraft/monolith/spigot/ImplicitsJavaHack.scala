package gg.warcraft.monolith.spigot

import org.bukkit.Server

// TODO delete class when MonolithPlugin class is ported to scala
class ImplicitsJavaHack {
  def doTheThing(server: Server): Unit = {
    Implicits.server = server
  }
}
