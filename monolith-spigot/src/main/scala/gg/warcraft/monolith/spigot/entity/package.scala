package gg.warcraft.monolith.spigot

import org.bukkit.entity.{Arrow, LivingEntity}
import org.bukkit.event.entity.{
  EntityDamageByEntityEvent, EntityDamageEvent, EntityDeathEvent, EntitySpawnEvent
}
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.Event

package object entity {
  type SpigotArrow = Arrow
  type SpigotEntity = LivingEntity
  type SpigotVector = org.bukkit.util.Vector

  type SpigotEvent = Event
  type SpigotEntityDamageEvent = EntityDamageEvent
  type SpigotEntityDamageByEntityEvent = EntityDamageByEntityEvent
  type SpigotEntityDeathEvent = EntityDeathEvent
  type SpigotEntitySpawnEvent = EntitySpawnEvent
  type SpigotEntityInteractEvent = PlayerInteractAtEntityEvent
}
