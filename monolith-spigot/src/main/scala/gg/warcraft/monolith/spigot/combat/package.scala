package gg.warcraft.monolith.spigot

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.{ ProjectileHitEvent, ProjectileLaunchEvent }
import org.bukkit.event.player.PlayerPickupArrowEvent
import org.bukkit.potion.PotionEffect

package object combat {
  type SpigotEntity = LivingEntity
  type SpigotPotionEffect = PotionEffect

  type SpigotProjectileLaunchEvent = ProjectileLaunchEvent
  type SpigotProjectileHitEvent = ProjectileHitEvent
  type SpigotProjectilePickupEvent = PlayerPickupArrowEvent
}
