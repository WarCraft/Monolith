package gg.warcraft.monolith.spigot.effect

import gg.warcraft.monolith.api.effect.Particle
import org.bukkit.{Particle => SpigotParticle}

class SpigotParticleMapper {
  def map(particle: SpigotParticle): Particle.Type =
    Particle.withName(particle.name())

  def map(particle: Particle.Type): SpigotParticle =
    SpigotParticle.valueOf(particle.toString)
}
