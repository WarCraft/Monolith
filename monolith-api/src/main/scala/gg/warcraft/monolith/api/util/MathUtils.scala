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

package gg.warcraft.monolith.api.util

import gg.warcraft.monolith.api.math.Vector3f

import scala.util.Random

object MathUtils {
  private final val TWO_PI = 2 * Math.PI.toFloat

  private val random = new Random(System.currentTimeMillis())

  def randomAngle: Float = random.nextFloat() * TWO_PI

  def randomVector: Vector3f = {
    val x = random.nextFloat() * 2f - 1f
    val y = random.nextFloat() * 2f - 1f
    val z = random.nextFloat() * 2f - 1f
    Vector3f(x, y, z).normalized
  }

  def randomCircleVector: Vector3f = {
    val radian = randomAngle
    val x = Math.cos(radian).toFloat
    val z = Math.sin(radian).toFloat
    Vector3f(x, 0, z)
  }
}
