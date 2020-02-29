package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.Vector3i
import io.getquill.Embedded

object BlockLocation {
  implicit def toWorld(loc: BlockLocation): World = loc.world
  implicit def toVector3i(loc: BlockLocation): Vector3i = loc.translation
  implicit def toLocation(loc: BlockLocation): Location =
    Location(loc.world, loc.translation)
}

case class BlockLocation(
    world: World,
    translation: Vector3i
) extends Embedded {
  val x: Int = translation.x
  val y: Int = translation.y
  val z: Int = translation.z

  def this(world: World, x: Int, y: Int, z: Int) =
    this(world, Vector3i(x, y, z))

  def add(x: Int, y: Int, z: Int): BlockLocation =
    copy(translation = translation.add(x, y, z))

  def add(vec: Vector3i): BlockLocation = add(vec.x, vec.y, vec.z)

  def add(loc: BlockLocation): BlockLocation = add(loc.x, loc.y, loc.z)

  def subtract(x: Int, y: Int, z: Int): BlockLocation =
    copy(translation = translation.subtract(x, y, z))

  def subtract(vec: Vector3i): BlockLocation = subtract(vec.x, vec.y, vec.z)

  def subtract(loc: BlockLocation): BlockLocation =
    subtract(loc.x, loc.y, loc.z)
}
