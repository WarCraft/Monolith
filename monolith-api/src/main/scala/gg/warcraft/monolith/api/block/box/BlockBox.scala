package gg.warcraft.monolith.api.block.box

import java.util.function.Predicate

import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.{BlockLocation, World}

case class BlockBox(
    world: World,
    min: Vector3i,
    max: Vector3i
) extends Predicate[BlockLocation] {
  require(min.x <= max.x, "min.x is > max.x, must be <= max.x")
  require(min.y <= max.y, "min.y is > max.y, must be <= max.y")
  require(min.z <= max.z, "min.z is > max.z, must be <= max.z")

  val north: Int = min.z
  val east: Int = max.x
  val south: Int = max.z
  val west: Int = min.x
  val upper: Int = max.y
  val lower: Int = min.y

  override def test(loc: BlockLocation): Boolean = {
    if (loc.world != world) false
    else {
      loc.x >= min.x && loc.x <= max.x &&
      loc.y >= min.y && loc.y <= max.y &&
      loc.z >= min.z && loc.z <= max.z
    }
  }

  def translate(vec: Vector3i): BlockBox =
    BlockBox(world, min.add(vec), max.add(vec))

  def rotateY(pivot: BlockLocation, degrees: Int): BlockBox = {
    require(degrees % 90 == 0, s"degrees is $degrees, must be a multiple of 90")

    val deltaNorth = pivot.z - north
    val deltaEast = east - pivot.x
    val deltaSouth = south - pivot.z
    val deltaWest = pivot.x - west

    var clampedDegrees = degrees
    while (clampedDegrees < 0) clampedDegrees += 360
    clampedDegrees %= 360
    clampedDegrees / 90 match {
      case 1 => // 90 degrees rotation
        copy(
          min = Vector3i(pivot.x - deltaSouth, lower, pivot.z - deltaWest),
          max = Vector3i(pivot.x + deltaNorth, upper, pivot.z + deltaEast)
        )
      case 2 => // 180 degrees rotation
        copy(
          min = Vector3i(pivot.x - deltaEast, lower, pivot.z - deltaSouth),
          max = Vector3i(pivot.x + deltaWest, upper, pivot.z + deltaNorth)
        )
      case 3 => // 270 degrees rotation
        copy(
          min = Vector3i(pivot.x - deltaNorth, lower, pivot.z - deltaEast),
          max = Vector3i(pivot.x + deltaSouth, upper, pivot.z + deltaWest)
        )
      case _ => this // 0 degrees rotation
    }
  }
}
