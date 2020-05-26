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

import gg.warcraft.monolith.api.block.BlockRotation
import org.bukkit.block.{BlockFace => SpigotBlockFace}

class SpigotBlockRotationMapper {
  def map(rotation: SpigotBlockFace): BlockRotation = rotation match {
    case SpigotBlockFace.NORTH            => BlockRotation.NORTH
    case SpigotBlockFace.NORTH_NORTH_EAST => BlockRotation.NORTH_NORTH_EAST
    case SpigotBlockFace.NORTH_EAST       => BlockRotation.NORTH_EAST
    case SpigotBlockFace.EAST_NORTH_EAST  => BlockRotation.EAST_NORTH_EAST
    case SpigotBlockFace.EAST             => BlockRotation.EAST
    case SpigotBlockFace.EAST_SOUTH_EAST  => BlockRotation.EAST_SOUTH_EAST
    case SpigotBlockFace.SOUTH_EAST       => BlockRotation.SOUTH_EAST
    case SpigotBlockFace.SOUTH_SOUTH_EAST => BlockRotation.SOUTH_SOUTH_EAST
    case SpigotBlockFace.SOUTH            => BlockRotation.SOUTH
    case SpigotBlockFace.SOUTH_SOUTH_WEST => BlockRotation.SOUTH_SOUTH_WEST
    case SpigotBlockFace.SOUTH_WEST       => BlockRotation.SOUTH_WEST
    case SpigotBlockFace.WEST_SOUTH_WEST  => BlockRotation.WEST_SOUTH_WEST
    case SpigotBlockFace.WEST             => BlockRotation.WEST
    case SpigotBlockFace.WEST_NORTH_WEST  => BlockRotation.WEST_NORTH_WEST
    case SpigotBlockFace.NORTH_WEST       => BlockRotation.NORTH_WEST
    case SpigotBlockFace.NORTH_NORTH_WEST => BlockRotation.NORTH_NORTH_WEST

    case _ => throw new IllegalArgumentException(s"$rotation")
  }

  def map(rotation: BlockRotation): SpigotBlockFace = rotation match {
    case BlockRotation.NORTH            => SpigotBlockFace.NORTH
    case BlockRotation.NORTH_NORTH_EAST => SpigotBlockFace.NORTH_NORTH_EAST
    case BlockRotation.NORTH_EAST       => SpigotBlockFace.NORTH_EAST
    case BlockRotation.EAST_NORTH_EAST  => SpigotBlockFace.EAST_NORTH_EAST
    case BlockRotation.EAST             => SpigotBlockFace.EAST
    case BlockRotation.EAST_SOUTH_EAST  => SpigotBlockFace.EAST_SOUTH_EAST
    case BlockRotation.SOUTH_EAST       => SpigotBlockFace.SOUTH_EAST
    case BlockRotation.SOUTH_SOUTH_EAST => SpigotBlockFace.SOUTH_SOUTH_EAST
    case BlockRotation.SOUTH            => SpigotBlockFace.SOUTH
    case BlockRotation.SOUTH_SOUTH_WEST => SpigotBlockFace.SOUTH_SOUTH_WEST
    case BlockRotation.SOUTH_WEST       => SpigotBlockFace.SOUTH_WEST
    case BlockRotation.WEST_SOUTH_WEST  => SpigotBlockFace.WEST_SOUTH_WEST
    case BlockRotation.WEST             => SpigotBlockFace.WEST
    case BlockRotation.WEST_NORTH_WEST  => SpigotBlockFace.WEST_NORTH_WEST
    case BlockRotation.NORTH_WEST       => SpigotBlockFace.NORTH_WEST
    case BlockRotation.NORTH_NORTH_WEST => SpigotBlockFace.NORTH_NORTH_WEST

    case _ => throw new IllegalArgumentException(s"$rotation")
  }
}
