package gg.warcraft.monolith.api.combat

import java.util.UUID

import gg.warcraft.monolith.api.block.{Block, BlockFace}
import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}

trait ProjectileEvent

// LAUNCH
case class ProjectilePreLaunchEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with ProjectileEvent

case class ProjectileLaunchEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID]
) extends Event
    with ProjectileEvent

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
) extends CancellableEvent
    with ProjectileEvent

case class ProjectileHitEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    block: Option[Block],
    blockFace: Option[BlockFace],
    entityId: Option[UUID]
) extends Event
    with ProjectileEvent

// PICKUP
case class ProjectilePrePickupEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    entityId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with ProjectileEvent

case class ProjectilePickupEvent(
    projectileId: UUID,
    projectileType: Projectile.Type,
    shooterId: Option[UUID],
    entityId: UUID
) extends Event
    with ProjectileEvent
