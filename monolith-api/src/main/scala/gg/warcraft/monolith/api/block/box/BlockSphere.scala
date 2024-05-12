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

import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.{BlockLocation, World}

import java.util.function.Predicate

case class BlockSphere(
    world: World,
    center: BlockLocation,
    radius: Int
) extends Predicate[BlockLocation] {
  lazy val diameter: Float = radius * 2
  lazy val size: Int = ((4 / 3) * Math.PI * Math.pow(radius, 3)).toInt

  override def test(loc: BlockLocation): Boolean =
    if (loc.world == world) {
      val dx = loc.x - center.x
      val dy = loc.y - center.y
      val dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))
      dist <= radius
    } else false

  def translate(vec: Vector3i, world: World = world): BlockSphere =
    BlockSphere(world, center + vec, radius)
}
