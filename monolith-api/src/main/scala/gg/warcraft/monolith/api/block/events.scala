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
) extends CancellableEvent
    with BlockEvent

case class BlockBreakEvent(
    block: Block,
    player: Player,
    alternativeDrops: Option[List[Item]]
) extends Event
    with BlockEvent

// INTERACT
case class BlockPreInteractEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with BlockEvent

case class BlockInteractEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player
) extends Event
    with BlockEvent

// PLACE
case class BlockPrePlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with BlockEvent

case class BlockPlaceEvent(
    block: Block,
    againstBlock: Block,
    againstBlockFace: BlockFace,
    player: Player
) extends Event
    with BlockEvent

// PUNCH
case class BlockPrePunchEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with BlockEvent

case class BlockPunchEvent(
    block: Block,
    blockFace: BlockFace,
    player: Player
) extends Event
    with BlockEvent

// TRIGGER
case class BlockPreTriggerEvent(
    block: Block,
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with BlockEvent

case class BlockTriggerEvent(
    block: Block,
    player: Player
) extends Event
    with BlockEvent
