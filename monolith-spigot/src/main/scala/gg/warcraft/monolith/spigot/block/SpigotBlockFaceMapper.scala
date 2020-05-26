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

import java.util

import gg.warcraft.monolith.api.block.BlockFace
import org.bukkit.block.{BlockFace => SpigotBlockFace}

import scala.jdk.CollectionConverters._

class SpigotBlockFaceMapper {
  def map(face: SpigotBlockFace): BlockFace = face match {
    case SpigotBlockFace.NORTH => BlockFace.NORTH
    case SpigotBlockFace.EAST  => BlockFace.EAST
    case SpigotBlockFace.SOUTH => BlockFace.SOUTH
    case SpigotBlockFace.WEST  => BlockFace.WEST
    case SpigotBlockFace.UP    => BlockFace.UP
    case SpigotBlockFace.DOWN  => BlockFace.DOWN

    case it => throw new IllegalArgumentException(s"$it")
  }

  def map(face: BlockFace): SpigotBlockFace = face match {
    case BlockFace.NORTH => SpigotBlockFace.NORTH
    case BlockFace.EAST  => SpigotBlockFace.EAST
    case BlockFace.SOUTH => SpigotBlockFace.SOUTH
    case BlockFace.WEST  => SpigotBlockFace.WEST
    case BlockFace.UP    => SpigotBlockFace.UP
    case BlockFace.DOWN  => SpigotBlockFace.DOWN
  }

  def map(facing: util.Set[SpigotBlockFace]): Set[BlockFace] =
    facing.asScala.map(map).toSet

  def map(facing: Set[BlockFace]): util.Set[SpigotBlockFace] =
    facing.map(map).asJava
}
