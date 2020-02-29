package gg.warcraft.monolith.java.world

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.java.math.Vector3f

object Location {
  private[java] implicit def fromScala(loc: ScalaLocation): Location =
    new Location(loc)
}

class Location private[java] (private[java] val asScala: ScalaLocation) {
  val world: World = asScala.world
  val translation: Vector3f = asScala.translation
  val x: Float = translation.x
  val y: Float = translation.y
  val z: Float = translation.z
  lazy val (pitch, yaw): (Float, Float) = asScala.rotation.toPitchYaw

  def this(world: World, transl: Vector3f, rotation: Vector3f) =
    this(new ScalaLocation(world, transl, rotation))
  def this(world: World, transl: Vector3f) =
    this(new ScalaLocation(world, transl))
  def this(world: World, x: Int, y: Int, z: Int) =
    this(world, new Vector3f(x, y, z))

  def withWorld(world: World): Location =
    asScala.copy(world = world)
  def withTranslation(transl: Vector3f): Location =
    asScala.copy(translation = transl)
  def withX(x: Float): Location =
    asScala.copy(translation = translation.asScala.copy(x = x))
  def withY(y: Float): Location =
    asScala.copy(translation = translation.asScala.copy(y = y))
  def withZ(z: Float): Location =
    asScala.copy(translation = translation.asScala.copy(z = z))
  def withRotation(rotation: Vector3f): Location =
    asScala.copy(rotation = rotation)
  def withPitch(pitch: Float): Location =
    asScala.copy(rotation = Vector3f.fromPitchYaw(pitch, yaw))
  def withYaw(yaw: Float): Location =
    asScala.copy(rotation = Vector3f.fromPitchYaw(pitch, yaw))

  def add(x: Float, y: Float, z: Float): Location = asScala.add(x, y, z)
  def add(vec: Vector3f): Location = asScala.add(vec)
  def add(loc: Location): Location = asScala.add(loc)

  def subtract(x: Float, y: Float, z: Float): Location = asScala.subtract(x, y, z)
  def subtract(vec: Vector3f): Location = asScala.subtract(vec)
  def subtract(loc: Location): Location = asScala.subtract(loc)

  def distanceTo(target: Location): Float =
    translation.distanceTo(target.translation)

  def toBlockLocation: BlockLocation =
    gg.warcraft.monolith.api.world.Location.toBlockLocation(asScala)
}
