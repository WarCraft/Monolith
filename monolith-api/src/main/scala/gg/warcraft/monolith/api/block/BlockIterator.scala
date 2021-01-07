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

package gg.warcraft.monolith.api.block

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.world.{BlockLocation, Location, WorldService}
import org.joml.Vector2f
import org.joml.primitives.{AABBf, Rayf}

import scala.annotation.tailrec

class BlockIterator(origin: Location, target: Location)(implicit
    worldService: WorldService
) extends Iterator[Block] {
  private val maxDistance = origin.distanceTo(target)
  private val direction = (target - origin).normalized

  private var distance = 0f
  private var scanLoc = origin
  private var blockLoc: BlockLocation = _
  private var nextBlockLoc: BlockLocation = origin

  @tailrec private def calculateNext(): BlockLocation = {
    var delta = Vector3f()
    if (distance + 1 < maxDistance) {
      delta = direction
      distance += 1

      scanLoc = scanLoc + delta
      if (Location.toBlockLocation(scanLoc) == blockLoc) calculateNext()
      else scanLoc
    } else {
      delta = direction * (maxDistance - distance)
      distance = maxDistance

      scanLoc = scanLoc + delta
      if (scanLoc.toBlockLocation == blockLoc) null
      else scanLoc
    }
  }

  override def hasNext: Boolean =
    nextBlockLoc != null

  override def next(): Block = {
    blockLoc = nextBlockLoc
    nextBlockLoc = calculateNext()
    worldService.getBlock(blockLoc)
  }

  def intersect(predicate: Block => Boolean): Option[Block.Intersection] = {
    while (hasNext) {
      val block = next()
      if (predicate.apply(block)) {
        val minCorner = blockLoc.toLocation.translation
        val boundingBox = new AABBf(minCorner, minCorner + (1, 1, 1))
        val iteratorRay = new Rayf(origin.translation, direction)
        val intersectionResult = new Vector2f()
        if (boundingBox.intersectsRay(iteratorRay, intersectionResult)) {
          val nearIntersectionScalar = intersectionResult.x
          val intersection = origin + (direction * nearIntersectionScalar)
          return Block.Intersection(block, intersection) |> Some.apply
        } else {
          throw new IllegalStateException(
            "Failed to calculate intersection for current block location in block iterator, this should not be possible."
          );
        }
      }
    }
    None
  }
}
