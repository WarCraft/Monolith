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

import io.getquill.Embedded

object Vector3i {
  implicit def fromInts(ints: (Int, Int, Int)): Vector3i =
    Vector3i(ints._1, ints._2, ints._3)

  implicit def toInts(vec: Vector3i): (Int, Int, Int) =
    (vec.x, vec.y, vec.z)
  implicit def toVector3f(vec: Vector3i): Vector3f =
    Vector3f(vec.x.toFloat, vec.y.toFloat, vec.z.toFloat)
}

case class Vector3i(
    x: Int = 0,
    y: Int = 0,
    z: Int = 0
) extends Embedded {
  def +(xyz: (Int, Int, Int)): Vector3i =
    copy(x = this.x + xyz._1, y = this.y + xyz._2, z = this.z + xyz._3)

  def -(xyz: (Int, Int, Int)): Vector3i =
    copy(x = this.x - xyz._1, y = this.y - xyz._2, z = this.z - xyz._3)

  def *(scalar: Int): Vector3i =
    copy(x = x * scalar, y = y * scalar, z = z * scalar)
  def *(xyz: (Int, Int, Int)): Vector3i =
    copy(x = x * xyz._1, y = y * xyz._2, z = z * xyz._3)
}
