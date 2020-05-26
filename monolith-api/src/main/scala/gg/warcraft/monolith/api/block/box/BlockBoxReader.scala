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

package gg.warcraft.monolith.api.block.box

import gg.warcraft.monolith.api.block.{Block, BlockType}
import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.{Direction, WorldService}

class BlockBoxReader(
    val box: BlockBox,
    val orientation: Direction
)(
    private implicit val worldService: WorldService
) {
  import box._

  private val readBlock: Vector3i => Block = orientation match {
    case Direction.NORTH =>
      vec => worldService.getBlock(world, west + vec.x, lower + vec.y, south - vec.z)
    case Direction.EAST =>
      vec => worldService.getBlock(world, west + vec.z, lower + vec.y, north + vec.x)
    case Direction.SOUTH =>
      vec => worldService.getBlock(world, east - vec.x, lower + vec.y, north + vec.z)
    case Direction.WEST =>
      vec => worldService.getBlock(world, east - vec.z, lower + vec.y, south - vec.x)
  }

  private val readOffset: Block => Vector3i = orientation match {
    case Direction.NORTH =>
      block => Vector3i(block.x - west, block.y - lower, south - block.z)
    case Direction.EAST =>
      block => Vector3i(block.z - north, block.y - lower, block.x - west)
    case Direction.SOUTH =>
      block => Vector3i(east - block.x, block.y - lower, block.z - north)
    case Direction.WEST =>
      block => Vector3i(south - block.z, block.y - lower, east - block.x)
  }

  def getBlock(offset: Vector3i): Block = readBlock(offset)

  def getOffset(block: Block): Vector3i = readOffset(block)

  def getBlocks: LazyList[Block] = getBlocks(locationGenerator)

  def getBlocks(types: BlockType*): LazyList[Block] = locationGenerator
    .map(it => worldService.getBlockIfType((world, it._1, it._2, it._3), types: _*))
    .filter(_.isDefined)
    .map(_.get)

  def sliceX(x: Int): LazyList[Block] = getBlocks(for {
    y <- LazyList.range(min.y, max.y + 1)
    z <- min.z to max.z
  } yield (x, y, z))

  def sliceY(y: Int): LazyList[Block] = getBlocks(for {
    x <- LazyList.range(min.x, max.x + 1)
    z <- min.z to max.z
  } yield (x, y, z))

  def sliceZ(z: Int): LazyList[Block] = getBlocks(for {
    x <- LazyList.range(min.x, max.x + 1)
    y <- min.y to max.y
  } yield (x, y, z))

  private def locationGenerator: LazyList[(Int, Int, Int)] = for {
    x <- LazyList.range(min.x, max.x + 1)
    y <- min.y to max.y
    z <- min.z to max.z
  } yield (x, y, z)

  private def getBlocks(generator: LazyList[(Int, Int, Int)]): LazyList[Block] =
    generator.map(it => worldService.getBlock(world, it._1, it._2, it._3))
}
