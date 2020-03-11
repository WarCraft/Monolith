package gg.warcraft.monolith.spigot.combat

import gg.warcraft.monolith.api.combat.PotionEffect
import org.bukkit.potion.{PotionEffectType => SpigotPotionEffectType}

class SpigotPotionEffectTypeMapper {
  def map(typed: SpigotPotionEffectType): PotionEffect.Type =
    PotionEffect.withName(typed.getName)

  def map(typed: PotionEffect.Type): SpigotPotionEffectType =
    SpigotPotionEffectType.getByName(typed.toString)
}
