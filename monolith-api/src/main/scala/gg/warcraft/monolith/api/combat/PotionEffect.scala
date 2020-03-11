package gg.warcraft.monolith.api.combat

import gg.warcraft.monolith.api.core.Duration

object PotionEffect extends Enumeration {
  type Type = Value
  val ABSORPTION, BAD_OMEN, BLINDNESS, CONDUIT_POWER, CONFUSION, DAMAGE_RESISTANCE,
      DOLPHINS_GRACE, FAST_DIGGING, FIRE_RESISTANCE, GLOWING, HARM, HEAL,
      HEALTH_BOOST, HERO_OF_THE_VILLAGE, HUNGER, INCREASE_DAMAGE, INVISIBILITY, JUMP,
      LEVITATION, LUCK, NIGHT_VISION, POISON, REGENERATION, SATURATION, SLOW,
      SLOW_DIGGING, SLOW_FALLING = Value

  def ambient(
      typed: PotionEffect.Type,
      level: Int,
      duration: Duration
  ): PotionEffect =
    PotionEffect(typed, level, duration, particles = true, ambient = true)

  def visual(
      typed: PotionEffect.Type,
      level: Int,
      duration: Duration,
      ambient: Boolean = false
  ): PotionEffect =
    PotionEffect(typed, level, duration, particles = true, ambient = ambient)
}

case class PotionEffect(
    typed: PotionEffect.Type,
    level: Int,
    duration: Duration,
    particles: Boolean = false,
    ambient: Boolean = false
)
