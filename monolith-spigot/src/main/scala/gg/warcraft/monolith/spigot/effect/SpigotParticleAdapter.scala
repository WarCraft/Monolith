package gg.warcraft.monolith.spigot.effect

import gg.warcraft.monolith.api.effect.{ParticleAdapter, ParticleColor}
import gg.warcraft.monolith.api.effect.Particle.Type
import gg.warcraft.monolith.api.world.Location
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import gg.warcraft.monolith.spigot.SpigotPlayer
import org.bukkit.Server

import scala.jdk.CollectionConverters._

class SpigotParticleAdapter(
    implicit server: Server,
    locationMapper: SpigotLocationMapper,
    particleMapper: SpigotParticleMapper
) extends ParticleAdapter {
  private final val playerType = classOf[SpigotPlayer]
  private final val zeroOffset = 0.0

  override def display(particle: Type, location: Location): Unit = {
    val spigotLocation = locationMapper map location
    val spigotParticle = particleMapper map particle
    spigotLocation
      .getNearbyEntitiesByType(playerType, DEFAULT_RANGE)
      .asScala
      .foreach { _.spawnParticle(spigotParticle, spigotLocation, 1) }
  }

  override def display(
      particle: Type,
      location: Location,
      color: ParticleColor
  ): Unit = {
    val spigotLocation = locationMapper map location
    val spigotParticle = particleMapper map particle
    val spigotColor = colorMapper map color
    spigotLocation
      .getNearbyEntitiesByType(playerType, DEFAULT_RANGE)
      .asScala
      .foreach { _.spawnParticle(spigotParticle, spigotLocation, 1, spigotColor) }
  }

  override def display(
      particle: Type,
      location: Location,
      speed: Float,
      amount: Int
  ): Unit = {
    val spigotLocation = locationMapper map location
    val spigotParticle = particleMapper map particle
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
