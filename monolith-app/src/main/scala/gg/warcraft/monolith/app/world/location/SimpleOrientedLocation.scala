package gg.warcraft.monolith.app.world.location

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.location.{BlockLocation, Location, OrientedLocation}
import org.joml.{Vector3f, Vector3fc}

case class SimpleOrientedLocation(
  world: World,
  x: Float,
  y: Float,
  z: Float,
  pitch: Float,
  yaw: Float
) extends OrientedLocation {
  validateWorld(world)
  validatePitch(pitch)
  validateYaw(yaw)

  lazy val direction: Vector3fc = orientationToDirection(pitch, yaw)

  override def getWorld: World = world

  override def getX: Float = x

  override def getY: Float = y

  override def getZ: Float = z

  override def getPitch: Float = pitch

  override def getYaw: Float = yaw

  override def add(x: Float, y: Float, z: Float): OrientedLocation =
    SimpleOrientedLocation(world, this.x + x, this.y + y, this.z + z, pitch, yaw)

  override def sub(x: Float, y: Float, z: Float): OrientedLocation =
    SimpleOrientedLocation(world, this.x - x, this.y - y, this.z - z, pitch, yaw)

  override def add(vector: Vector3fc): OrientedLocation =
    add(vector.x, vector.y, vector.z)

  override def sub(vector: Vector3fc): OrientedLocation =
    sub(vector.x, vector.y, vector.z)

  override def add(location: Location): OrientedLocation =
    add(location.getX, location.getY, location.getZ)

  override def sub(location: Location): OrientedLocation =
    sub(location.getX, location.getY, location.getZ)

  override def withWorld(world: World): OrientedLocation = copy(world = world)

  override def withX(x: Float): OrientedLocation = copy(x = x)

  override def withY(y: Float): OrientedLocation = copy(y = y)

  override def withZ(z: Float): OrientedLocation = copy(z = z)

  override def withPitch(pitch: Float): OrientedLocation = copy(pitch = pitch)

  override def withYaw(yaw: Float): OrientedLocation = copy(yaw = yaw)

  override def withOrientation(pitch: Float, yaw: Float): OrientedLocation =
    copy(pitch = pitch, yaw = yaw)

  override def toBlockLocation: BlockLocation =
    SimpleBlockLocation(world, x.toInt, y.toInt, z.toInt)

  override def toDirection: Vector3f = new Vector3f(direction)

  override def toVector: Vector3f = new Vector3f(x, y, z)
}
