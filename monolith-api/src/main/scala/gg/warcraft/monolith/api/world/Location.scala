package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}
import io.getquill.Embedded

object Location {
  implicit def fromTupled(params: (World, Float, Float, Float)): Location =
    Location(params._1, (params._2, params._3, params._4))
  implicit def fromTupled(params: (World, Vector3f)): Location =
    Location(params._1, params._2)
  implicit def fromTupled(params: (World, Vector3f, Vector3f)): Location =
    Location(params._1, params._2, params._3)

  implicit def toWorld(loc: Location): World =
    loc.world
  implicit def toVector3f(loc: Location): Vector3f =
    loc.translation
  implicit def toFloats(loc: Location): (Float, Float, Float) =
    (loc.x, loc.y, loc.z)
  implicit def toPitchYaw(loc: Location): (Float, Float) =
    (loc.pitch, loc.yaw)

  implicit def toBlockLocation(loc: Location): BlockLocation = {
    val floor = (x: Float) => Math.floor(x).toInt
    val translation = Vector3i(floor(loc.x), floor(loc.y), floor(loc.z))
    BlockLocation(loc.world, translation)
  }
}

case class Location(
    world: World,
    translation: Vector3f,
    rotation: Vector3f = Vector3f.ZERO_PITCH_YAW
) extends Embedded {
  val x: Float = translation.x
  val y: Float = translation.y
  val z: Float = translation.z

  lazy val (pitch, yaw): (Float, Float) = rotation.toPitchYaw

  def this(world: World, x: Float, y: Float, z: Float) =
    this(world, Vector3f(x, y, z), Vector3f.ZERO_PITCH_YAW)

  def add(xyz: (Float, Float, Float)): Location =
    copy(translation = translation.add(xyz._1, xyz._2, xyz._3))

  def subtract(xyz: (Float, Float, Float)): Location =
    copy(translation = translation.subtract(xyz._1, xyz._2, xyz._3))

  def distanceTo(target: Location): Float =
    translation distanceTo target

  def toBlockLocation: BlockLocation =
    Location toBlockLocation this
}
