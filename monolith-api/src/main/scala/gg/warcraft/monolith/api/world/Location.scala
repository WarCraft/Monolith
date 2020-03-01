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

  implicit def toWorld(loc: Location): World = loc.world
  implicit def toVector3f(loc: Location): Vector3f = loc.translation
  implicit def toFloats(loc: Location): (Float, Float, Float) = (loc.x, loc.y, loc.z)
  implicit def toPitchYaw(loc: Location): (Float, Float) = (loc.pitch, loc.yaw)
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

  def add(x: Float, y: Float, z: Float): Location =
    copy(translation = translation.add(x, y, z))

  def add(vec: Vector3f): Location = add(vec.x, vec.y, vec.z)

  def add(loc: Location): Location = add(loc.x, loc.y, loc.z)

  def subtract(x: Float, y: Float, z: Float): Location =
    copy(translation = translation.subtract(x, y, z))

  def subtract(vec: Vector3f): Location = subtract(vec.x, vec.y, vec.z)

  def subtract(loc: Location): Location = subtract(loc.x, loc.y, loc.z)

  def distanceTo(target: Location): Float =
    translation.distanceTo(target.translation)

  // TODO remove after entire project is converted to Scala
  def withWorld(world: World): Location =
    copy(world = world)
  def withTranslation(transl: Vector3f): Location =
    copy(translation = transl)
  def withX(x: Float): Location =
    copy(translation = translation.copy(x = x))
  def withY(y: Float): Location =
    copy(translation = translation.copy(y = y))
  def withZ(z: Float): Location =
    copy(translation = translation.copy(z = z))
  def withRotation(rotation: Vector3f): Location =
    copy(rotation = rotation)
  def withPitch(pitch: Float): Location =
    copy(rotation = Vector3f(pitch, yaw))
  def withYaw(yaw: Float): Location =
    copy(rotation = Vector3f(pitch, yaw))
}
