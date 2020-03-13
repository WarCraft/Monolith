package gg.warcraft.monolith.spigot.combat

import java.util.UUID

import gg.warcraft.monolith.api.combat.{
  ProjectileHitEvent, ProjectileLaunchEvent, ProjectilePickupEvent,
  ProjectilePreHitEvent, ProjectilePreLaunchEvent, ProjectilePrePickupEvent,
  ProjectileType
}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.spigot.block.{
  SpigotBlockFaceMapper, SpigotBlockMapper
}
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class SpigotCombatEventMapper(
    implicit private val eventService: EventService,
    implicit private val blockMapper: SpigotBlockMapper,
    implicit private val blockFaceMapper: SpigotBlockFaceMapper
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
      ProjectileType.ARROW,
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
      ProjectileType.ARROW,
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
      case it   => blockMapper.map(it)
    }
    val blockFace = event.getHitBlockFace match {
      case null => None
      case it   => Some(blockFaceMapper.map(it))
    }
    val entityId = getEntityId(event.getHitEntity)

    var preHitEvent = ProjectilePreHitEvent(
      projectile.getUniqueId,
      ProjectileType.ARROW,
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
      case it   => blockMapper.map(it)
    }
    val blockFace = event.getHitBlockFace match {
      case null => None
      case it   => Some(blockFaceMapper.map(it))
    }
    val entityId = getEntityId(event.getHitEntity)

    val hitEvent = ProjectileHitEvent(
      projectile.getUniqueId,
      ProjectileType.ARROW,
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
      ProjectileType.ARROW,
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
      ProjectileType.ARROW,
      shooterId,
      pickupEntityId
    )
    eventService.publish(pickupEvent)
  }
}
