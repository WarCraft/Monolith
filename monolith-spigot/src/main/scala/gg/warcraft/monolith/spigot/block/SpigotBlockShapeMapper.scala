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

import gg.warcraft.monolith.api.block._
import gg.warcraft.monolith.api.block.shape.{RailsShape, StairsShape}
import gg.warcraft.monolith.spigot.Extensions._
import org.bukkit.block.data.Rail.{Shape => SpigotRailsShape}
import org.bukkit.block.data.`type`.Stairs.{Shape => SpigotStairsShape}

class SpigotBlockShapeMapper {
  def map(block: SpigotBlock): BlockShape = {
    val data: SpigotBlockData = block.getState.getBlockData

    block.getType match {
      case it if it.isRail =>
        val shape = data.asInstanceOf[SpigotRail].getShape
        map(shape)

      case it if it.isStairs =>
        val shape = data.asInstanceOf[SpigotStairs].getShape
        map(shape)

      case _ => throw new IllegalArgumentException(s"${block.getType}")
    }
  }

  def map(block: ShapedBlock[_], spigotData: SpigotBlockData): Unit = {
    val data: SpigotBlockData = spigotData

    block.shape match {
      case it: RailsShape =>
        val shape = map(it)
        data.asInstanceOf[SpigotRail].setShape(shape)

      case it: StairsShape =>
        val shape = map(it)
        data.asInstanceOf[SpigotStairs].setShape(shape)

      case _ => throw new IllegalArgumentException(s"${block.`type`}")
    }
  }

  def map(shape: SpigotRailsShape): RailsShape = shape match {
    case SpigotRailsShape.NORTH_EAST  => RailsShape.NORTH_EAST
    case SpigotRailsShape.NORTH_SOUTH => RailsShape.NORTH_SOUTH
    case SpigotRailsShape.NORTH_WEST  => RailsShape.NORTH_WEST
    case SpigotRailsShape.EAST_WEST   => RailsShape.EAST_WEST
    case SpigotRailsShape.SOUTH_EAST  => RailsShape.SOUTH_EAST
    case SpigotRailsShape.SOUTH_WEST  => RailsShape.SOUTH_WEST

    case SpigotRailsShape.ASCENDING_NORTH => RailsShape.ASCENDING_NORTH
    case SpigotRailsShape.ASCENDING_EAST  => RailsShape.ASCENDING_EAST
    case SpigotRailsShape.ASCENDING_SOUTH => RailsShape.ASCENDING_SOUTH
    case SpigotRailsShape.ASCENDING_WEST  => RailsShape.ASCENDING_WEST
  }

  def map(shape: RailsShape): SpigotRailsShape = shape match {
    case RailsShape.NORTH_EAST  => SpigotRailsShape.NORTH_EAST
    case RailsShape.NORTH_SOUTH => SpigotRailsShape.NORTH_SOUTH
    case RailsShape.NORTH_WEST  => SpigotRailsShape.NORTH_WEST
    case RailsShape.EAST_WEST   => SpigotRailsShape.EAST_WEST
    case RailsShape.SOUTH_EAST  => SpigotRailsShape.SOUTH_EAST
    case RailsShape.SOUTH_WEST  => SpigotRailsShape.SOUTH_WEST

    case RailsShape.ASCENDING_NORTH => SpigotRailsShape.ASCENDING_NORTH
    case RailsShape.ASCENDING_EAST  => SpigotRailsShape.ASCENDING_EAST
    case RailsShape.ASCENDING_SOUTH => SpigotRailsShape.ASCENDING_SOUTH
    case RailsShape.ASCENDING_WEST  => SpigotRailsShape.ASCENDING_WEST
  }

  def map(shape: SpigotStairsShape): StairsShape = shape match {
    case SpigotStairsShape.STRAIGHT    => StairsShape.STRAIGHT
    case SpigotStairsShape.INNER_LEFT  => StairsShape.INNER_LEFT
    case SpigotStairsShape.INNER_RIGHT => StairsShape.INNER_RIGHT
    case SpigotStairsShape.OUTER_LEFT  => StairsShape.OUTER_LEFT
    case SpigotStairsShape.OUTER_RIGHT => StairsShape.OUTER_RIGHT
  }

  def map(shape: StairsShape): SpigotStairsShape = shape match {
    case StairsShape.STRAIGHT    => SpigotStairsShape.STRAIGHT
    case StairsShape.INNER_LEFT  => SpigotStairsShape.INNER_LEFT
    case StairsShape.INNER_RIGHT => SpigotStairsShape.INNER_RIGHT
    case StairsShape.OUTER_LEFT  => SpigotStairsShape.OUTER_LEFT
    case StairsShape.OUTER_RIGHT => SpigotStairsShape.OUTER_RIGHT
  }
}
