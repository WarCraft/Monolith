package gg.warcraft.monolith.spigot.effect

import gg.warcraft.monolith.api.effect.{ParticleAdapter, ParticleColor}
import gg.warcraft.monolith.api.effect.Particle.Type
import gg.warcraft.monolith.api.world.Location

class SpigotParticleAdapter extends ParticleAdapter {
  override def display(particle: Type, location: Location): Unit = ???

  override def display(
      particle: Type,
      location: Location,
      color: ParticleColor
  ): Unit = ???

  override def display(
      particle: Type,
      location: Location,
      speed: Float,
      amount: Int
  ): Unit = ???
}
