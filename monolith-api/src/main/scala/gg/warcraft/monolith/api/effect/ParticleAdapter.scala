package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.world.Location

trait ParticleAdapter {
  protected final val DEFAULT_RANGE: Float = 64
  protected final val DEFAULT_HALF_RANGE: Float = DEFAULT_RANGE / 2

  def display(particle: Particle.Type, location: Location): Unit

  def display(
      particle: Particle.Type,
      location: Location,
      color: Particle.Color
  ): Unit

  def display(
      particle: Particle.Type,
      location: Location,
      speed: Float,
      amount: Int
  ): Unit
}
