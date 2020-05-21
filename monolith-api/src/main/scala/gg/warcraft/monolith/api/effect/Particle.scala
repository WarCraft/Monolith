package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.world.Location

sealed trait Particle {
  def display(location: Location)(implicit
      adapter: ParticleAdapter
  ): Unit = adapter.display(this, location)

  def color(color: Particle.Color): Particle =
    new ColorParticle(this, color)

  def speed(speed: Float, amount: Int): Particle =
    new SpeedParticle(this, speed, amount)

  def multi(particles: Particle*): Particle =
    new MultiParticle(particles: _*)
}

private class ColorParticle(
    particle: Particle,
    color: Particle.Color
) extends Particle {
  override def display(location: Location)(implicit
      adapter: ParticleAdapter
  ): Unit = adapter.display(particle, location, color)
}

private class SpeedParticle(
    particle: Particle,
    speed: Float,
    amount: Int
) extends Particle {
  override def display(location: Location)(implicit
      adapter: ParticleAdapter
  ): Unit = adapter.display(particle, location, speed, amount)
}

private class MultiParticle(
    particles: Particle*
) extends Particle {
  private var iterator = particles.iterator

  override def display(location: Location)(implicit
      adapter: ParticleAdapter
  ): Unit = {
    if (!iterator.hasNext) iterator = particles.iterator
    if (iterator.hasNext) iterator.next.display(location)
  }
}

object Particle {
  case object Barrier extends Particle
  case object BlockCrack extends Particle
  case object BlockDust extends Particle
  case object BubbleColumnUp extends Particle
  case object BubblePop extends Particle
  case object CampfireCosySmoke extends Particle
  case object CampfireSignalSmoke extends Particle
  case object Cloud extends Particle
  case object Composter extends Particle
  case object Crit extends Particle
  case object CritMagic extends Particle
  case object CurrentDown extends Particle
  case object DamageIndicator extends Particle
  case object Dolphin extends Particle
  case object DragonBreath extends Particle
  case object DripLava extends Particle
  case object DripWater extends Particle
  case object DrippingHoney extends Particle
  case object EnchantmentTable extends Particle
  case object EndRod extends Particle
  case object ExplosionHuge extends Particle
  case object ExplosionLarge extends Particle
  case object ExplosionNormal extends Particle
  case object FallingDust extends Particle
  case object FallingHoney extends Particle
  case object FallingLava extends Particle
  case object FallingNectar extends Particle
  case object FallingWater extends Particle
  case object FireworksSpark extends Particle
  case object Flame extends Particle
  case object Flash extends Particle
  case object Heart extends Particle
  case object ItemCrack extends Particle
  case object LandingHoney extends Particle
  case object LandingLava extends Particle
  case object Lava extends Particle
  case object LegacyBlockCrack extends Particle
  case object LegacyBlockDust extends Particle
  case object LegacyFallingDust extends Particle
  case object MobAppearance extends Particle
  case object Nautilus extends Particle
  case object Note extends Particle
  case object Portal extends Particle
  case object Redstone extends Particle
  case object Slime extends Particle
  case object SmokeLarge extends Particle
  case object SmokeNormal extends Particle
  case object Sneeze extends Particle
  case object SnowShovel extends Particle
  case object Snowball extends Particle
  case object Spell extends Particle
  case object SpellInstant extends Particle
  case object SpellMob extends Particle
  case object SpellMobAmbient extends Particle
  case object SpellWitch extends Particle
  case object Spit extends Particle
  case object SquidInk extends Particle
  case object Suspended extends Particle
  case object SuspendedDepth extends Particle
  case object SweepAttack extends Particle
  case object Totem extends Particle
  case object TownAura extends Particle
  case object VillagerAngry extends Particle
  case object VillagerHappy extends Particle
  case object WaterBubble extends Particle
  case object WaterDrop extends Particle
  case object WaterSplash extends Particle
  case object WaterWake extends Particle

  sealed trait Color
  object Color {
    case object Aqua extends Color
    case object Black extends Color
    case object Blue extends Color
    case object Fuchsia extends Color
    case object Gray extends Color
    case object Green extends Color
    case object Lime extends Color
    case object Maroon extends Color
    case object Navy extends Color
    case object Olive extends Color
    case object Orange extends Color
    case object Purple extends Color
    case object Red extends Color
    case object Silver extends Color
    case object Teal extends Color
    case object White extends Color
    case object Yellow extends Color
  }
}
