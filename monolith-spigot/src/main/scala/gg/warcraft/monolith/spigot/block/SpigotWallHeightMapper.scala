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

import gg.warcraft.monolith.api.block.{BlockFace, WallHeight}
import org.bukkit.block.data.`type`.Wall.{Height => SpigotWallHeight}
import org.bukkit.block.{BlockFace => SpigotBlockFace}

class SpigotWallHeightMapper {
  def map(height: SpigotWallHeight): WallHeight = height match {
    case SpigotWallHeight.NONE => WallHeight.NONE
    case SpigotWallHeight.LOW  => WallHeight.LOW
    case SpigotWallHeight.TALL => WallHeight.TALL
    case null                  => WallHeight.NONE
  }

  def map(height: WallHeight): SpigotWallHeight = height match {
    case WallHeight.NONE => SpigotWallHeight.NONE
    case WallHeight.LOW  => SpigotWallHeight.LOW
    case WallHeight.TALL => SpigotWallHeight.TALL
  }

  def map(wall: SpigotWall): Map[BlockFace, WallHeight] = Map(
    BlockFace.UP -> map(wall.getHeight(SpigotBlockFace.UP)),
    BlockFace.NORTH -> map(wall.getHeight(SpigotBlockFace.NORTH)),
    BlockFace.EAST -> map(wall.getHeight(SpigotBlockFace.EAST)),
    BlockFace.SOUTH -> map(wall.getHeight(SpigotBlockFace.SOUTH)),
    BlockFace.WEST -> map(wall.getHeight(SpigotBlockFace.WEST))
  )

  def map(wall: SpigotWall, heights: Map[BlockFace, WallHeight]): Unit = {
    wall.setHeight(
      SpigotBlockFace.UP,
      map(heights.getOrElse(BlockFace.UP, WallHeight.NONE))
    )
    wall.setHeight(
      SpigotBlockFace.NORTH,
      map(heights.getOrElse(BlockFace.NORTH, WallHeight.NONE))
    )
    wall.setHeight(
      SpigotBlockFace.EAST,
      map(heights.getOrElse(BlockFace.EAST, WallHeight.NONE))
    )
    wall.setHeight(
      SpigotBlockFace.SOUTH,
      map(heights.getOrElse(BlockFace.SOUTH, WallHeight.NONE))
    )
    wall.setHeight(
      SpigotBlockFace.WEST,
      map(heights.getOrElse(BlockFace.WEST, WallHeight.NONE))
    )
  }
}
