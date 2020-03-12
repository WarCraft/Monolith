package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.world.Location

object Particle extends Enumeration {
  private[monolith] var adapter: ParticleAdapter = _

  type Type = Value
  implicit def toVal(v: Value): Val = v.asInstanceOf[Val]
  implicit def toParticle(v: Value): Particle = v.asInstanceOf[Particle]
  protected case class Val() extends super.Val with Particle {
    override def display(location: Location): Unit =
      Particle.adapter.display(this, location)
    def withColor(color: ParticleColor): Particle =
      new ColorParticle(this, color)
    def withSpeed(speed: Float, amount: Int): Particle =
      new SpeedParticle(this, speed, amount)
  }

  val BARRIER, BLOCK_CRACK, BLOCK_DUST, BUBBLE_COLUMN_UP, BUBBLE_POP,
  CAMPFIRE_COSY_SMOKE, CAMPFIRE_SIGNAL_SMOKE, CLOUD, COMPOSTER, CRIT, CRIT_MAGIC,
  CURRENT_DOWN, DAMAGE_INDICATOR, DOLPHIN, DRAGON_BREATH, DRIP_LAVA, DRIP_WATER,
  DRIPPING_HONEY, ENCHANTMENT_TABLE, END_ROD, EXPLOSION_HUGE, EXPLOSION_LARGE,
  EXPLOSION_NORMAL, FALLING_DUST, FALLING_HONEY, FALLING_LAVA, FALLING_NECTAR,
  FALLING_WATER, FIREWORKS_SPARK, FLAME, FLASH, HEART, ITEM_CRACK, LANDING_HONEY,
  LANDING_LAVA, LAVA, LEGACY_BLOCK_CRACK, LEGACY_BLOCK_DUST, LEGACY_FALLING_DUST,
  MOB_APPEARANCE, NAUTILUS, NOTE, PORTAL, REDSTONE, SLIME, SMOKE_LARGE,
  SMOKE_NORMAL, SNEEZE, SNOW_SHOVEL, SNOWBALL, SPELL, SPELL_INSTANT, SPELL_MOB,
  SPELL_MOB_AMBIENT, SPELL_WITCH, SPIT, SQUID_INK, SUSPENDED, SUSPENDED_DEPTH,
  SWEEP_ATTACK, TOTEM, TOWN_AURA, VILLAGER_ANGRY, VILLAGER_HAPPY, WATER_BUBBLE,
  WATER_DROP, WATER_SPLASH, WATER_WAKE = Val
}

sealed trait Particle {
  def display(location: Location): Unit
  def multiWith(particles: Particle*): Particle = new MultiParticle(particles: _*)
}

private class ColorParticle(
    particle: Particle.Type,
    color: ParticleColor
) extends Particle {
  override def display(location: Location): Unit =
    Particle.adapter.display(particle, location, color)
}

private class SpeedParticle(
    particle: Particle.Type,
    speed: Float,
    amount: Int
) extends Particle {
  override def display(location: Location): Unit =
    Particle.adapter.display(particle, location, speed, amount)
}

private class MultiParticle(particles: Particle*) extends Particle {
  private var iterator = particles.iterator
  override def display(location: Location): Unit = {
    if (!iterator.hasNext) iterator = particles.iterator
    if (iterator.hasNext) iterator.next display location
  }
}
