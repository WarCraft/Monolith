package gg.warcraft.monolith.api.block

import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}
import gg.warcraft.monolith.api.item.Item
import gg.warcraft.monolith.api.player.Player

trait BlockEvent

// BREAK
case class BlockPreBreakEvent(
    block: Block,
    player: Player,
    alternativeDrops: Option[List[Item]],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockBreakEvent(
    block: Block,
    player: Player,
    alternativeDrops: Option[List[Item]]
) extends BlockEvent
    with Event

// INTERACT
case class BlockPreInteractEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockInteractEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player
) extends BlockEvent
    with Event

// PLACE
case class BlockPrePlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockPlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    player: Player
) extends BlockEvent
    with Event

// PUNCH
case class BlockPrePunchEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockPunchEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player
) extends BlockEvent
    with Event

// TRIGGER
case class BlockPreTriggerEvent(
    block: Block,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends BlockEvent
    with CancellableEvent

case class BlockTriggerEvent(
    block: Block,
    player: Player
) extends BlockEvent
    with Event
