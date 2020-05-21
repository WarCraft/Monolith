package gg.warcraft.monolith.spigot.effect

import gg.warcraft.monolith.api.effect.{Particle, ParticleAdapter}
import gg.warcraft.monolith.api.world.Location
import gg.warcraft.monolith.spigot.combat.SpigotEntity
import gg.warcraft.monolith.spigot.player.SpigotPlayer
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.Server

import scala.jdk.CollectionConverters._

class SpigotParticleAdapter(implicit
    server: Server,
    locationMapper: SpigotLocationMapper,
    particleMapper: SpigotParticleMapper
) extends ParticleAdapter {
  private final val playerType = classOf[SpigotPlayer]
  private final val zeroOffset = 0.0

  override def display(particle: Particle, location: Location): Unit = {
    val spigotLocation = locationMapper.map(location)
    val spigotParticle = particleMapper.map(particle)
    spigotLocation
      .getNearbyEntitiesByType(playerType, DEFAULT_RANGE)
      .asScala
      .foreach { _.spawnParticle(spigotParticle, spigotLocation, 1) }
  }

  override def display(
      particle: Particle,
      location: Location,
      color: Particle.Color
  ): Unit = {
    val spigotLocation = locationMapper.map(location)
    val spigotParticle = particleMapper.map(particle)
    val spigotColor = particleMapper.map(color)
    spigotLocation
      .getNearbyEntitiesByType(playerType, DEFAULT_RANGE)
      .asScala
      .foreach {
        case it: SpigotEntity =>
          it.spawnParticle(spigotParticle, spigotLocation, 1, spigotColor)
        case _ =>
      }
  }

  override def display(
      particle: Particle,
      location: Location,
      speed: Float,
      amount: Int
  ): Unit = {
    val spigotLocation = locationMapper.map(location)
    val spigotParticle = particleMapper.map(particle)
    spigotLocation
      .getNearbyEntitiesByType(playerType, DEFAULT_RANGE, DEFAULT_HALF_RANGE)
      .asScala
      .foreach {
        _.spawnParticle(
          spigotParticle,
          spigotLocation,
          amount,
          zeroOffset,
          zeroOffset,
          zeroOffset,
          speed.toDouble
        )
      }
  }
}
