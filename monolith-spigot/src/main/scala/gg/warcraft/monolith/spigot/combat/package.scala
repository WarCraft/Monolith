package gg.warcraft.monolith.spigot

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.{ProjectileHitEvent, ProjectileLaunchEvent}
import org.bukkit.event.player.PlayerPickupArrowEvent

package object combat {
  type SpigotEntity = LivingEntity

  type SpigotProjectileLaunchEvent = ProjectileLaunchEvent
  type SpigotProjectileHitEvent = ProjectileHitEvent
  type SpigotProjectilePickupEvent = PlayerPickupArrowEvent
}
