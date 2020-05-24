package gg.warcraft.monolith.spigot

import org.bukkit.{Location, Server}
import org.bukkit.entity.EntityType
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.event.entity.EntityDeathEvent

class TempCowSpawner(implicit server: Server) extends Listener {

  @EventHandler
  def onEntityDeath(event: EntityDeathEvent): Unit = {
    event.getEntity.getLocation.getWorld.spawnEntity(
      new Location(event.getEntity.getLocation().getWorld, 157, 55, -134),
      EntityType.COW
    )
  }
}
