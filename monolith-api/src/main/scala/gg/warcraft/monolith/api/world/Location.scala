package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}

case class Location(
    world: World,
    translation: Vector3f,
    rotation: Vector3f = Vector3f.ZERO_PITCH_YAW
) {
  val x: Float = translation.x
  val y: Float = translation.y
  val z: Float = translation.z

  private lazy val pitchYaw: (Float, Float) = rotation.toPitchYaw

  lazy val pitch: Float = pitchYaw._1
  lazy val yaw: Float = pitchYaw._2

  def this(world: World, x: Float, y: Float, z: Float) =
    this(world, Vector3f(x, y, z), Vector3f.ZERO_PITCH_YAW)

  def add(x: Float, y: Float, z: Float): Location =
    copy(translation = translation.add(x, y, z))

  def add(vec: Vector3f): Location =
    add(vec.x, vec.y, vec.z)

  def add(loc: Location): Location =
    add(loc.x, loc.y, loc.z)

  def subtract(x: Float, y: Float, z: Float): Location =
    copy(translation = translation.subtract(x, y, z))

  def subtract(vec: Vector3f): Location =
    subtract(vec.x, vec.y, vec.z)

  def subtract(loc: Location): Location =
    subtract(loc.x, loc.y, loc.z)

  def distanceTo(target: Location): Float =
    translation.distanceTo(target.translation)

  def toBlockLocation: BlockLocation = {
    val floor = (x: Float) => Math.floor(x).toInt
    val translation = Vector3i(floor(x), floor(y), floor(z))
    BlockLocation(world, translation)
  }

  /* Java interop */

  def this(world: World, translation: Vector3f) =
    this(world, translation, Vector3f.ZERO_PITCH_YAW)

  def withWorld(world: World): Location =
    copy(world = world)

  def withTranslation(translation: Vector3f): Location =
    copy(translation = translation)

  def withRotation(rotation: Vector3f): Location =
    copy(rotation = rotation)

  def withX(x: Float): Location =
    copy(translation = translation.withX(x))

  def withY(y: Float): Location =
    copy(translation = translation.withY(y))

  def withZ(z: Float): Location =
    copy(translation = translation.withZ(z))

  def withPitch(pitch: Float): Location =
    copy(rotation = Vector3f.apply(pitch, yaw))

  def withYaw(yaw: Float): Location =
    copy(rotation = Vector3f.apply(pitch, yaw))
}
