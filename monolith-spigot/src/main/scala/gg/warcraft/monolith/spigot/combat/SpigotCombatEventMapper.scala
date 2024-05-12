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

package gg.warcraft.monolith.spigot.combat

import java.util.UUID

import gg.warcraft.monolith.api.combat.{
  Projectile, ProjectileHitEvent, ProjectileLaunchEvent, ProjectilePickupEvent,
  ProjectilePreHitEvent, ProjectilePreLaunchEvent, ProjectilePrePickupEvent
}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.spigot.block.{SpigotBlockFaceMapper, SpigotBlockMapper}
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class SpigotCombatEventMapper(implicit
    eventService: EventService,
    blockMapper: SpigotBlockMapper,
    blockFaceMapper: SpigotBlockFaceMapper
) extends Listener {
  private def getEntityId(shooter: Any): Option[UUID] = shooter match {
    case it: SpigotEntity => Some(it.getUniqueId)
    case _                => None
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preProjectileLaunch(event: SpigotProjectileLaunchEvent): Unit = {
    val projectile = event.getEntity
    val shooter = projectile.getShooter
    val shooterId = getEntityId(shooter)

    var preLaunchEvent = ProjectilePreLaunchEvent(
      projectile.getUniqueId,
      Projectile.ARROW,
      shooterId,
      event.isCancelled
    )
    preLaunchEvent = eventService.publish(preLaunchEvent)
    event.setCancelled(!preLaunchEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onProjectileLaunch(event: SpigotProjectileLaunchEvent): Unit = {
    val projectile = event.getEntity
    val shooter = projectile.getShooter
    val shooterId = getEntityId(shooter)

    val launchEvent = ProjectileLaunchEvent(
      projectile.getUniqueId,
      Projectile.ARROW,
      shooterId
    )
    eventService.publish(launchEvent)
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preProjectileHit(event: SpigotProjectileHitEvent): Unit = {
    val projectile = event.getEntity
    val shooter = projectile.getShooter
    val shooterId = getEntityId(shooter)
    val block = event.getHitBlock match {
      case null => None
      case it   => blockMapper.mapBlockToOption(it)
    }
    val blockFace = event.getHitBlockFace match {
      case null => None
      case it   => Some(blockFaceMapper.map(it))
    }
    val entityId = getEntityId(event.getHitEntity)

    var preHitEvent = ProjectilePreHitEvent(
      projectile.getUniqueId,
      Projectile.ARROW,
      shooterId,
      block,
      blockFace,
      entityId,
      projectile.doesBounce()
    )
    preHitEvent = eventService.publish(preHitEvent)

    if (!preHitEvent.allowed) {
      projectile.setBounce(true)
      projectile.remove()
    } else projectile.setBounce(false)
  }

  @EventHandler(priority = EventPriority.MONITOR)
  def onProjectileHit(event: SpigotProjectileHitEvent): Unit = {
    val projectile = event.getEntity
    if (projectile.doesBounce() || projectile.isDead) return

    val shooter = projectile.getShooter
    val shooterId = getEntityId(shooter)
    val block = event.getHitBlock match {
      case null => None
      case it   => blockMapper.mapBlockToOption(it)
    }
    val blockFace = event.getHitBlockFace match {
      case null => None
      case it   => Some(blockFaceMapper.map(it))
    }
    val entityId = getEntityId(event.getHitEntity)

    val hitEvent = ProjectileHitEvent(
      projectile.getUniqueId,
      Projectile.ARROW,
      shooterId,
      block,
      blockFace,
      entityId
    )
    eventService.publish(hitEvent)
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preProjectilePickup(event: SpigotProjectilePickupEvent): Unit = {
    val projectile = event.getArrow
    val shooter = projectile.getShooter
    val shooterId = getEntityId(shooter)
    val pickupEntityId = event.getPlayer.getUniqueId

    var prePickupEvent = ProjectilePrePickupEvent(
      projectile.getUniqueId,
      Projectile.ARROW,
      shooterId,
      pickupEntityId,
      event.isCancelled
    )
    prePickupEvent = eventService.publish(prePickupEvent)
    event.setCancelled(!prePickupEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onProjectilePickup(event: SpigotProjectilePickupEvent): Unit = {
    val projectile = event.getArrow
    val shooter = projectile.getShooter
    val shooterId = getEntityId(shooter)
    val pickupEntityId = event.getPlayer.getUniqueId

    val pickupEvent = ProjectilePickupEvent(
      projectile.getUniqueId,
      Projectile.ARROW,
      shooterId,
      pickupEntityId
    )
    eventService.publish(pickupEvent)
  }
}
