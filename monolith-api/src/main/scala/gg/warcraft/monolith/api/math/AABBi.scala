package gg.warcraft.monolith.api.math

import gg.warcraft.monolith.api.world.BlockLocation

case class AABBi(
    minX: Int,
    minY: Int,
    minZ: Int,
    maxX: Int,
    maxY: Int,
    maxZ: Int
) {
  val north: Int = minZ
  val east: Int = maxX
  val south: Int = maxZ
  val west: Int = minX
  val upper: Int = maxY
  val lower: Int = minY

  def contains(loc: BlockLocation): Boolean =
    minX <= loc.x && loc.x <= maxX &&
      minY <= loc.y && loc.y <= maxY &&
      minZ <= loc.z && loc.z <= maxZ
}
