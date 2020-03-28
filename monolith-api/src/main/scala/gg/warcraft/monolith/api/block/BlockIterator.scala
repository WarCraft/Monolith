package gg.warcraft.monolith.api.block

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{BlockLocation, Location, WorldService}

import scala.annotation.tailrec

class BlockIterator(origin: Location, target: Location)(
    implicit worldService: WorldService
) extends Iterator[Block] {
  private val maxDistance = origin.distanceTo(target)
  private val direction = target.subtract(origin).normalize

  private var distance = 0f
  private var scanLoc = origin
  private var blockLoc: BlockLocation = _
  private var nextBlockLoc: BlockLocation = origin

  @tailrec private def calculateNext(): BlockLocation = {
    var delta = Vector3f()
    if (distance + 1 < maxDistance) {
      delta = direction
      distance += 1

      scanLoc = scanLoc add delta
      if (Location.toBlockLocation(scanLoc) == blockLoc) calculateNext()
      else scanLoc
    } else {
      delta = direction multiply (maxDistance - distance)
      distance = maxDistance

      scanLoc = scanLoc add delta
      // TODO add toBlockLocation method back to Location which forwards to implicit def?
      if (Location.toBlockLocation(scanLoc) == blockLoc) null
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
      if (predicate apply block) {
        val intersection = blockLoc add (.5f, .5f, .5f)
        // FIXME
        // System.out.println("DEBUG Calculating intersection for " + currentBlockLocation);
        // Vector3f blockMinimumCorner = currentBlockLocation.toLocation().toVector();
        // AABBf blockBoundingBox = new AABBf(blockMinimumCorner, blockMinimumCorner.add(1, 1, 1, new Vector3f()));
        // Rayf iteratorRay = new Rayf(origin.toVector(), direction);
        // Vector2f intersectionResult = new Vector2f();
        // if (blockBoundingBox.intersectRay(iteratorRay, intersectionResult)) {
        //     float nearIntersectionScalar = intersectionResult.x;
        //     return origin.add(direction.mul(nearIntersectionScalar, new Vector3f()));
        // } else {
        //     throw new IllegalStateException("Failed to calculate intersection for current block location in block " +
        //             "iterator, this should not be possible.");
        // }
        // TODO get rid of return statement
        return Some(Block.Intersection(block, intersection))
      }
    }
    None
  }
}
