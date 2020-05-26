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

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}
import io.getquill.Embedded

object Location {
  implicit def fromTupled(params: (World, Float, Float, Float)): Location =
    Location(params._1, (params._2, params._3, params._4))
  implicit def fromTupled(params: (World, Vector3f)): Location =
    Location(params._1, params._2)
  implicit def fromTupled(params: (World, Vector3f, Vector3f)): Location =
    Location(params._1, params._2, params._3)

  implicit def toWorld(loc: Location): World =
    loc.world
  implicit def toVector3f(loc: Location): Vector3f =
    loc.translation
  implicit def toFloats(loc: Location): (Float, Float, Float) =
    (loc.x, loc.y, loc.z)
  implicit def toPitchYaw(loc: Location): (Float, Float) =
    (loc.pitch, loc.yaw)

  implicit def toBlockLocation(loc: Location): BlockLocation = {
    val floor = (x: Float) => Math.floor(x).toInt
    val translation = Vector3i(floor(loc.x), floor(loc.y), floor(loc.z))
    BlockLocation(loc.world, translation)
  }
}

case class Location(
    world: World,
    translation: Vector3f,
    rotation: Vector3f = Vector3f.ZERO_PITCH_YAW
) extends Embedded {
  val x: Float = translation.x
  val y: Float = translation.y
  val z: Float = translation.z

  lazy val (pitch, yaw): (Float, Float) = rotation.toPitchYaw

  def this(world: World, x: Float, y: Float, z: Float) =
    this(world, Vector3f(x, y, z), Vector3f.ZERO_PITCH_YAW)

  def +(xyz: (Float, Float, Float)): Location =
    copy(translation = translation + xyz)

  def -(xyz: (Float, Float, Float)): Location =
    copy(translation = translation - xyz)

  def distanceTo(target: Location): Float =
    translation distanceTo target

  def toBlockLocation: BlockLocation =
    Location toBlockLocation this
}
