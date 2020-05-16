package gg.warcraft.monolith.api.block

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.world.{BlockLocation, Location, WorldService}
import org.joml.{AABBf, Rayf, Vector2f}

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
        if (boundingBox.intersectRay(iteratorRay, intersectionResult)) {
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
