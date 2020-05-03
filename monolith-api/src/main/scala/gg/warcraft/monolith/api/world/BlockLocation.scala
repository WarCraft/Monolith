package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.Vector3i
import io.getquill.Embedded

object BlockLocation {
  implicit def fromTupled(params: (World, Int, Int, Int)): BlockLocation =
    BlockLocation(params._1, (params._2, params._3, params._4))
  implicit def fromTupled(params: (World, Vector3i)): BlockLocation =
    BlockLocation(params._1, params._2)

  implicit def toWorld(loc: BlockLocation): World =
    loc.world
  implicit def toVector3i(loc: BlockLocation): Vector3i =
    loc.translation
  implicit def toInts(loc: BlockLocation): (Int, Int, Int) =
    (loc.x, loc.y, loc.z)
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

  def +(xyz: (Int, Int, Int)): BlockLocation =
    copy(translation = translation + xyz)

  def -(xyz: (Int, Int, Int)): BlockLocation =
    copy(translation = translation - xyz)

  def toLocation: Location =
    BlockLocation toLocation this
}
