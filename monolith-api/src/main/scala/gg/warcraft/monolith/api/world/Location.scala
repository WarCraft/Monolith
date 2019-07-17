package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.Vector3f

case class Location(
  world: World,
  translation: Vector3f,
  rotation: Vector3f
) {
  val x: Float = translation.x
  val y: Float = translation.y
  val z: Float = translation.z

  private lazy val pitchYaw: (Float, Float) = rotation.toPitchYaw
  lazy val pitch: Float = pitchYaw._1
  lazy val yaw: Float = pitchYaw._2

  def add(x: Float, y: Float, z: Float): Location =
    copy(translation = translation.add(x, y, z))

  def add(vec: Vector3f): Location = add(vec.x, vec.y, vec.z)

  def add(loc: Location): Location = add(loc.x, loc.y, loc.z)

  def sub(x: Float, y: Float, z: Float): Location =
    copy(translation = translation.sub(x, y, z))

  def sub(vec: Vector3f): Location = sub(vec.x, vec.y, vec.z)

  def sub(loc: Location): Location = sub(loc.x, loc.y, loc.z)

  // TODO check if truncating the floating point gets the correct block coordinates
  // TODO I expect this to be problematic with negative coordinates
  def toBlockLocation: BlockLocation = BlockLocation(world, translation.toVector3i)

  /* Java interop */

  def this(world: World, translation: Vector3f) {
    // TODO a zero vector probably doesn't represent zero pitch and yaw
    this(world, translation, Vector3f())
  }

  def getWorld: World = world
  def getTranslation: Vector3f = translation
  def getRotation: Vector3f = rotation
  def getX: Float = translation.x
  def getY: Float = translation.y
  def getZ: Float = translation.z
  def getPitch: Float = pitch
  def getYaw: Float = yaw

  def withWorld(world: World): Location = copy(world = world)
  def withTranslation(translation: Vector3f): Location = copy(translation = translation)
  def withRotation(rotation: Vector3f): Location = copy(rotation = rotation)
  def withX(x: Float): Location = copy(translation = translation.withX(x))
  def withY(y: Float): Location = copy(translation = translation.withY(y))
  def withZ(z: Float): Location = copy(translation = translation.withZ(z))
  def withPitch(pitch: Float): Location = copy(rotation = Vector3f.apply(pitch, yaw))
  def withYaw(yaw: Float): Location = copy(rotation = Vector3f.apply(pitch, yaw))
}
