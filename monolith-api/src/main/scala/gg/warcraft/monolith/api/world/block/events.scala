package gg.warcraft.monolith.api.world.block

import java.util.UUID

import gg.warcraft.monolith.api.core.{CancellableEvent, Event}
import gg.warcraft.monolith.api.world.item.Item

// BREAK
case class BlockPreBreakEvent(
    block: Block,
    playerId: UUID,
    alternativeDrops: Option[List[Item]],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent

case class BlockBreakEvent(
    block: Block,
    playerId: UUID,
    alternativeDrops: Option[List[Item]]
) extends Event

// INTERACT
case class BlockPreInteractEvent(
    block: Option[Block],
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent

case class BlockInteractEvent(
    block: Option[Block],
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item]
) extends Event

// PLACE
case class BlockPrePlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    playerId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent

case class BlockPlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    playerId: UUID
) extends Event

// PUNCH
case class BlockPrePunchEvent(
    block: Option[Block],
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent

case class BlockPunchEvent(
    block: Option[Block],
    blockFace: BlockFace,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item]
) extends Event

// TRIGGER
case class BlockPreTriggerEvent(
    block: Block,
    playerId: UUID,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent

case class BlockTriggerEvent(
    block: Block,
    playerId: UUID
) extends Event
