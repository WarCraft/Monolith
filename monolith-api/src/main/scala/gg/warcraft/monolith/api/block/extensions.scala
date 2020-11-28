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

import gg.warcraft.monolith.api.world.WorldService

object extensions {
  private val allBlockFaces =
    BlockFace.values.toList
  private val horizontalBlockFaces =
    BlockFace.NORTH :: BlockFace.EAST :: BlockFace.SOUTH :: BlockFace.WEST :: Nil
  private val verticalBlockFaces =
    BlockFace.UP :: BlockFace.DOWN :: Nil

  @inline implicit final class FindOps(private val self: Block) extends AnyVal {
    def getRelative(direction: BlockFace)(implicit
        worldService: WorldService
    ): Block = getRelatives(direction :: Nil).head

    def getRelatives(directions: List[BlockFace] = allBlockFaces)(implicit
        worldService: WorldService
    ): List[Block] = directions.map {
      case BlockFace.NORTH => worldService.getBlock(self.location + (0, 0, -1))
      case BlockFace.EAST  => worldService.getBlock(self.location + (1, 0, 0))
      case BlockFace.SOUTH => worldService.getBlock(self.location + (0, 0, 1))
      case BlockFace.WEST  => worldService.getBlock(self.location + (-1, 0, 0))
      case BlockFace.UP    => worldService.getBlock(self.location + (0, 1, 0))
      case BlockFace.DOWN  => worldService.getBlock(self.location + (0, -1, 0))
    }

    def getHorizontalRelatives()(implicit
        worldService: WorldService
    ): List[Block] = getRelatives(horizontalBlockFaces)

    def getVerticalRelatives()(implicit
        worldService: WorldService
    ): List[Block] = getRelatives(verticalBlockFaces)
  }
}
