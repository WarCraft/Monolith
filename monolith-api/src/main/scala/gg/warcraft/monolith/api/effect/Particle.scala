/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.core.Color
import gg.warcraft.monolith.api.world.Location

sealed trait Particle {
  def display(location: Location)(implicit
      adapter: ParticleAdapter
  ): Unit = adapter.display(this, location)

  def color(color: Color): Particle =
    new ColorParticle(this, color)

  def speed(speed: Float, amount: Int): Particle =
    new SpeedParticle(this, speed, amount)
}

private class ColorParticle(
    particle: Particle,
    color: Color
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

  def multi(particles: Particle*): Particle =
    new MultiParticle(particles: _*)
}
