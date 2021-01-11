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

package gg.warcraft.monolith.spigot.effect

import gg.warcraft.monolith.api.core.Color
import gg.warcraft.monolith.api.effect.{Particle, ParticleAdapter}
import gg.warcraft.monolith.api.world.Location
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
      .foreach {
        _.spawnParticle(
          spigotParticle,
          spigotLocation,
          1,
          zeroOffset,
          zeroOffset,
          zeroOffset,
          0
        )
      }
  }

  override def display(
      particle: Particle,
      location: Location,
      color: Color
  ): Unit = {
    val spigotLocation = locationMapper.map(location)
    val spigotParticle = particleMapper.map(particle)
    val spigotColor = particleMapper.map(color)
    spigotLocation
      .getNearbyEntitiesByType(playerType, DEFAULT_RANGE)
      .asScala
      .foreach {
        _.spawnParticle(
          spigotParticle,
          spigotLocation,
          1,
          zeroOffset,
          zeroOffset,
          zeroOffset,
          0,
          spigotColor
        )
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
      .getNearbyEntitiesByType(playerType, DEFAULT_RANGE)
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
