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

import java.util.function.Predicate

import gg.warcraft.monolith.api.block.Block
import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.{BlockLocation, Location, World}

import scala.util.chaining._

case class BlockBox(
    world: World,
    min: Vector3i,
    max: Vector3i
) extends Predicate[BlockLocation] {
  require(min.x <= max.x, "min.x is > max.x, must be <= max.x")
  require(min.y <= max.y, "min.y is > max.y, must be <= max.y")
  require(min.z <= max.z, "min.z is > max.z, must be <= max.z")

  val north: Int = min.z
  val east: Int = max.x
  val south: Int = max.z
  val west: Int = min.x
  val upper: Int = max.y
  val lower: Int = min.y

  lazy val width: Int = east - west
  lazy val height: Int = upper - lower
  lazy val length: Int = south - north
  lazy val size: Int = width * height * length

  lazy val halfWidth: Float = width / 2f
  lazy val halfHeight: Float = height / 2f
  lazy val halfLength: Float = length / 2f

  lazy val center: Location =
    Location(world, (west + halfWidth, lower + halfHeight, south + halfLength))
  lazy val floorCenter: Location =
    Location(world, (west + halfWidth, lower.toFloat, south + halfLength))

  lazy val centerBlock: BlockLocation = center.toBlockLocation
  lazy val floorCenterBlock: BlockLocation = floorCenter.toBlockLocation

  override def test(loc: BlockLocation): Boolean = {
    if (loc.world == world) {
      loc.x >= min.x && loc.x <= max.x &&
      loc.y >= min.y && loc.y <= max.y &&
      loc.z >= min.z && loc.z <= max.z
    } else false
  }

  def translate(vec: Vector3i, world: World = world): BlockBox =
    BlockBox(world, min + vec, max + vec)

  def move(loc: BlockLocation): BlockBox = {
    val translation = loc - floorCenterBlock
    translate(translation, loc.world)
  }

  def rotateY(degrees: Int, pivot: BlockLocation = floorCenterBlock): BlockBox = {
    require(degrees % 90 == 0, s"degrees is $degrees, must be a multiple of 90")

    val deltaNorth = pivot.z - north
    val deltaEast = east - pivot.x
    val deltaSouth = south - pivot.z
    val deltaWest = pivot.x - west

    var clampedDegrees = degrees
    while (clampedDegrees < 0) clampedDegrees += 360
    clampedDegrees %= 360
    clampedDegrees / 90 match {
      case 1 => // 90 degrees rotation
        copy(
          min = Vector3i(pivot.x - deltaSouth, lower, pivot.z - deltaWest),
          max = Vector3i(pivot.x + deltaNorth, upper, pivot.z + deltaEast)
        )
      case 2 => // 180 degrees rotation
        copy(
          min = Vector3i(pivot.x - deltaEast, lower, pivot.z - deltaSouth),
          max = Vector3i(pivot.x + deltaWest, upper, pivot.z + deltaNorth)
        )
      case 3 => // 270 degrees rotation
        copy(
          min = Vector3i(pivot.x - deltaNorth, lower, pivot.z - deltaEast),
          max = Vector3i(pivot.x + deltaSouth, upper, pivot.z + deltaWest)
        )
      case _ => this // 0 degrees rotation
    }
  }
}

object BlockBox {
  private final val blockBoxAccumulator: (World, Int, Int, Int, Int, Int, Int) = {
    import Int.{MaxValue, MinValue}
    (null, MaxValue, MaxValue, MaxValue, MinValue, MinValue, MinValue)
  }

  def apply(blocks: Iterable[Block]): BlockBox =
    if (blocks.nonEmpty) {
      blocks
        .foldLeft(blockBoxAccumulator) { (boundingBox, block) =>
          import block.location._
          (
            if (boundingBox._1 == null || boundingBox._1 == world) world
            else throw new IllegalArgumentException("all block worlds must _ == _"),
            Math.min(boundingBox._2, x),
            Math.min(boundingBox._3, y),
            Math.min(boundingBox._4, z),
            Math.max(boundingBox._5, x),
            Math.max(boundingBox._6, y),
            Math.max(boundingBox._7, z)
          )
        }
        .pipe { boundingBox =>
          import boundingBox._
          val min = Vector3i(_2, _3, _4)
          val max = Vector3i(_5, _6, _7)
          BlockBox(_1, min, max)
        }
    } else throw new IllegalArgumentException("blocks must be nonEmpty")
}
