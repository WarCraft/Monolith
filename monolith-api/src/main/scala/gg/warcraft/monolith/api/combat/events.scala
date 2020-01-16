package gg.warcraft.monolith.api.combat

import java.util.UUID

import gg.warcraft.monolith.api.core.event.CancellableEvent
import gg.warcraft.monolith.api.world.block.{Block, BlockFace}

trait ProjectileEvent

// HIT
case class ProjectilePreHitEvent(
    projectileId: UUID,
    projectileType: ProjectileType,
    shooterId: UUID,
    block: Option[Block],
    blockFace: Option[BlockFace],
    entityId: Option[UUID],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends ProjectileEvent
    with CancellableEvent

case class ProjectileHitEvent(
    projectileId: UUID,
    projectileType: ProjectileType,
    shooterId: UUID,
    block: Option[Block],
    blockFace: Option[BlockFace],
    entityId: Option[UUID]
) extends ProjectileEvent

// LAUNCH
case class ProjectilePreLaunchEvent(
    projectileId: UUID,
    projectileType: ProjectileType,
    shooterId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends ProjectileEvent
    with CancellableEvent

case class ProjectileLaunchEvent(
    projectileId: UUID,
    projectileType: ProjectileType,
    shooterId: UUID
) extends ProjectileEvent

// PICKUP
case class ProjectilePrePickupEvent(
    projectileId: UUID,
    projectileType: ProjectileType,
    shooterId: UUID,
    entityId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends ProjectileEvent
    with CancellableEvent

case class ProjectilePickupEvent(
    projectileId: UUID,
    projectileType: ProjectileType,
    shooterId: UUID,
    entityId: UUID
) extends ProjectileEvent
