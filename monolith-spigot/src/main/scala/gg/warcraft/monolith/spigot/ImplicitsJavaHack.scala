package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.api.entity.player.service.{
  PlayerCommandService, PlayerQueryService
}
import gg.warcraft.monolith.api.entity.service.{
  EntityCommandService, EntityQueryService
}
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

  def doTheOtherThing(
      eqs: EntityQueryService,
      ecs: EntityCommandService,
      pqs: PlayerQueryService,
      pcs: PlayerCommandService
  ): Unit = {
    Implicits.setEntityQueryService(eqs)
    Implicits.setEntityCommandService(ecs)
    Implicits.setPlayerQueryService(pqs)
    Implicits.setPlayerCommandService(pcs)
  }
}
