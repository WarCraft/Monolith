package gg.warcraft.monolith.api.combat

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}
import gg.warcraft.monolith.api.block.{Block, BlockFace}

trait ProjectileEvent

// LAUNCH
case class ProjectilePreLaunchEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends ProjectileEvent
    with CancellableEvent

case class ProjectileLaunchEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID]
) extends ProjectileEvent
    with Event

// HIT
case class ProjectilePreHitEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    block: Option[Block],
    blockFace: Option[BlockFace],
    entityId: Option[UUID],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends ProjectileEvent
    with CancellableEvent

case class ProjectileHitEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    block: Option[Block],
    blockFace: Option[BlockFace],
    entityId: Option[UUID]
) extends ProjectileEvent
    with Event

// PICKUP
case class ProjectilePrePickupEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    entityId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends ProjectileEvent
    with CancellableEvent

case class ProjectilePickupEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    entityId: UUID
) extends ProjectileEvent
    with Event
