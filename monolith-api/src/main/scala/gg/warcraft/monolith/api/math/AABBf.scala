package gg.warcraft.monolith.api.math

import gg.warcraft.monolith.api.world.Location

case class AABBf(
    minX: Float,
    minY: Float,
    minZ: Float,
    maxX: Float,
    maxY: Float,
    maxZ: Float
) {
  val north: Float = minZ
  val east: Float = maxX
  val south: Float = maxZ
  val west: Float = minX
  val upper: Float = maxY
  val lower: Float = minY

  def contains(loc: Location): Boolean =
    minX <= loc.x && loc.x <= maxX &&
      minY <= loc.y && loc.y <= maxY &&
      minZ <= loc.z && loc.z <= maxZ
}
