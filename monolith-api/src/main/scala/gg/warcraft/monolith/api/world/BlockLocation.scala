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

package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.Vector3i
import io.getquill.Embedded

object BlockLocation {
  implicit def fromTupled(params: (World, Int, Int, Int)): BlockLocation =
    BlockLocation(params._1, (params._2, params._3, params._4))
  implicit def fromTupled(params: (World, Vector3i)): BlockLocation =
    BlockLocation(params._1, params._2)

  implicit def toWorld(loc: BlockLocation): World =
    loc.world
  implicit def toVector3i(loc: BlockLocation): Vector3i =
    loc.translation
  implicit def toInts(loc: BlockLocation): (Int, Int, Int) =
    (loc.x, loc.y, loc.z)
  implicit def toLocation(loc: BlockLocation): Location =
    Location(loc.world, loc.translation)
}

case class BlockLocation(
    world: World,
    translation: Vector3i
) extends Embedded {
  val x: Int = translation.x
  val y: Int = translation.y
  val z: Int = translation.z

  def this(world: World, x: Int, y: Int, z: Int) =
    this(world, Vector3i(x, y, z))

  def +(xyz: (Int, Int, Int)): BlockLocation =
    copy(translation = translation + xyz)

  def -(xyz: (Int, Int, Int)): BlockLocation =
    copy(translation = translation - xyz)

  def toLocation: Location =
    BlockLocation toLocation this
}
