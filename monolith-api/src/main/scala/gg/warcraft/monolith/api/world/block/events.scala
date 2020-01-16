package gg.warcraft.monolith.api.world.block

import java.util.UUID

import gg.warcraft.monolith.api.core.CancellableEvent
import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}
import gg.warcraft.monolith.api.world.item.Item

trait BlockEvent

// BREAK
case class BlockPreBreakEvent(
    block: Block,
    playerId: UUID,
    alternativeDrops: Option[List[Item]],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockBreakEvent(
    block: Block,
    playerId: UUID,
    alternativeDrops: Option[List[Item]]
) extends BlockEvent
    with Event

// INTERACT
case class BlockPreInteractEvent(
    block: Block,
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockInteractEvent(
    block: Block,
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item]
) extends BlockEvent
    with Event

// PLACE
case class BlockPrePlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    playerId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockPlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    playerId: UUID
) extends BlockEvent
    with Event

// PUNCH
case class BlockPrePunchEvent(
    block: Block,
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockPunchEvent(
    block: Block,
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item]
) extends BlockEvent
    with Event

// TRIGGER
case class BlockPreTriggerEvent(
    block: Block,
    playerId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockTriggerEvent(
    block: Block,
    playerId: UUID
) extends BlockEvent
    with Event
