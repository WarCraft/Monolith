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

package gg.warcraft.monolith.spigot.block

import gg.warcraft.monolith.api.block.{ Block, BlockService, BlockType,
  BlockTypeVariantOrState }
import gg.warcraft.monolith.api.block.box.{ BlockBox, BlockBoxReader }
import gg.warcraft.monolith.api.world.{ BlockLocation, Direction, Location,
  WorldService }

class SpigotBlockService(
    implicit worldService: WorldService
) extends BlockService {
  // TODO move remaining block methods from world service to block service

  override def parseData(data: String): BlockTypeVariantOrState = ???

  override def getBlock(loc: BlockLocation): Block = ???

  override def getBlockIfType(loc: BlockLocation, types: BlockType*): Option[Block] = ???

  override def getHighestBlock(loc: BlockLocation): Block = ???

  override def getNearbyBlocks(location: Location, radius: Float): List[Block] = {
    val min: BlockLocation = location - (radius, radius, radius)
    val max: BlockLocation = location - (radius, radius, radius)
    val boundingBox = BlockBox(location.world, min, max)
    val reader = new BlockBoxReader(boundingBox, Direction.NORTH)
    reader.getBlocks.filter { block =>
      val center = BlockLocation.toLocation(block.location) + (.5f, .5f, .5f)
      (location distanceTo center) <= radius
    }.toList
  }

  override def setBlock(loc: BlockLocation, data: BlockTypeVariantOrState): Unit = ???

  override def setBlock(loc: BlockLocation, block: Block): Unit = ???
}
