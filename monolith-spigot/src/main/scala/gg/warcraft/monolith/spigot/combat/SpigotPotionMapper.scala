package gg.warcraft.monolith.spigot.combat

import gg.warcraft.monolith.api.combat.PotionEffect
import gg.warcraft.monolith.api.core.Duration._
import org.bukkit.potion.{PotionEffectType => SpigotPotionEffectType}

class SpigotPotionMapper {
  def map(typed: SpigotPotionEffectType): PotionEffect.Type =
    PotionEffect.withName(typed.getName)

  def map(typed: PotionEffect.Type): SpigotPotionEffectType =
    SpigotPotionEffectType.getByName(typed.toString)

  def map(effect: PotionEffect): SpigotPotionEffect = {
    import effect._
    new SpigotPotionEffect(map(typed), duration.ticks, level, ambient, particles)
  }

  def map(effect: SpigotPotionEffect): PotionEffect = {
    import effect._
    PotionEffect(
      map(getType),
      getAmplifier,
      getDuration.seconds,
      hasParticles,
      isAmbient
    )
  }
}
