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

package gg.warcraft.monolith.api.math

import gg.warcraft.monolith.api.util.number._
import io.getquill.Embedded

object Vector3f {
  val ZERO_PITCH_YAW: Vector3f = Vector3f(0, 0, 1)

  def apply(x: Double, y: Double, z: Double): Vector3f =
    Vector3f(x.toFloat, y.toFloat, z.toFloat)

  def apply(pitch: Float, yaw: Float): Vector3f = {
    require(pitch >=< (-90, 90), s"pitch is $pitch, must be >= -90 and <= 90")

    var clampedYaw = yaw
    while (clampedYaw < -180f) clampedYaw += 360f
    while (clampedYaw >= 180f) clampedYaw -= 360f

    // Add 90 degrees to correct for an extra rotation Minecraft added
    val pitchRad = Math.toRadians(pitch + 90f)
    val yawRad = Math.toRadians(clampedYaw + 90f)

    val sinPitchRad = Math.sin(pitchRad)
    val x = sinPitchRad * Math.cos(yawRad)
    val y = sinPitchRad * Math.sin(yawRad)
    val z = Math.cos(pitchRad)

    // Normally the Z-axis defines height, but in Minecraft this is the Y-axis
    Vector3f(x.toFloat, z.toFloat, y.toFloat)
  }

  object Offset {
    val aboveHead: Vector3f = Vector3f(y = 2f)
    val head: Vector3f = Vector3f(y = 1.5f)
    val chest: Vector3f = Vector3f(y = 1f)
    val hands: Vector3f = Vector3f(y = 0.6f)
    val legs: Vector3f = Vector3f(y = .3f)
    val feet: Vector3f = Vector3f(y = .1f)
  }

  implicit def fromFloats(floats: (Float, Float, Float)): Vector3f =
    Vector3f(floats._1, floats._2, floats._3)
  implicit def fromJomlVector3f(vec: JomlVector3fc): Vector3f =
    Vector3f(vec.x, vec.y, vec.z)

  implicit def toFloats(vec: Vector3f): (Float, Float, Float) =
    (vec.x, vec.y, vec.z)
  implicit def toVector3i(vec: Vector3f): Vector3i =
    Vector3i(vec.x.toInt, vec.y.toInt, vec.z.toInt)
  implicit def toJomlVector3f(vec: Vector3f): JomlVector3f =
    new JomlVector3f(vec.x, vec.y, vec.z)
}

case class Vector3f(
    x: Float = 0,
    y: Float = 0,
    z: Float = 0
) extends Embedded {
  private lazy val jomlVector = new JomlVector3f(x, y, z)
  private lazy val jomlOut = new JomlVector3f()

  lazy val lengthSquared: Float = x * x + y * y + z * z
  lazy val length: Float = Math.sqrt(lengthSquared).toFloat
  lazy val inverseLength: Float = 1f / length
  lazy val normalized: Vector3f = this * inverseLength

  def +(xyz: (Float, Float, Float)): Vector3f =
    copy(x = this.x + xyz._1, y = this.y + xyz._2, z = this.z + xyz._3)

  def -(xyz: (Float, Float, Float)): Vector3f =
    copy(x = this.x - xyz._1, y = this.y - xyz._2, z = this.z - xyz._3)

  def *(scalar: Float): Vector3f =
    copy(x = x * scalar, y = y * scalar, z = z * scalar)
  def *(xyz: (Float, Float, Float)): Vector3f =
    copy(x = x * xyz._1, y = y * xyz._2, z = z * xyz._3)

  def /(scalar: Float): Vector3f =
    copy(x = x / scalar, y = y / scalar, z = z / scalar)
  def /(xyz: (Float, Float, Float)): Vector3f =
    copy(x = x / xyz._1, y = y / xyz._2, z = z / xyz._3)

  def rotateX(angle: Float): Vector3f = {
    jomlVector.rotateX(angle, jomlOut)
    jomlOut
  }

  def rotateY(angle: Float): Vector3f = {
    jomlVector.rotateY(angle, jomlOut)
    jomlOut
  }

  def rotateZ(angle: Float): Vector3f = {
    jomlVector.rotateZ(angle, jomlOut)
    jomlOut
  }

  def distanceTo(target: Vector3f): Float =
    (target - this).length

  def toPitchYaw: (Float, Float) = {
    val pitchRad = Math.asin(-normalized.y)
    val yawRad = Math.atan2(normalized.x, normalized.z)

    val pitch = Math.toDegrees(pitchRad)
    val unboundYaw = -Math.toDegrees(yawRad)
    val yaw = if (unboundYaw == 180f) -180f else unboundYaw
    (pitch.toFloat, yaw.toFloat)
  }
}
