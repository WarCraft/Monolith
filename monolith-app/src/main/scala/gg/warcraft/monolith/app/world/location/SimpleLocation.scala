package gg.warcraft.monolith.app.world.location

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.location.{BlockLocation, Location, OrientedLocation}
import org.joml.{Vector3f, Vector3fc}

case class SimpleLocation(
  world: World,
  x: Float,
  y: Float,
  z: Float
) extends Location {
  validateWorld(world)

  override def getWorld: World = world

  override def getX: Float = x

  override def getY: Float = y

  override def getZ: Float = z

  override def add(x: Float, y: Float, z: Float): Location =
    SimpleLocation(world, this.x + x, this.y + y, this.z + z)

  override def sub(x: Float, y: Float, z: Float): Location =
    SimpleLocation(world, this.x - x, this.y - y, this.z - z)

  override def add(vector: Vector3fc): Location =
    add(vector.x, vector.y, vector.z)

  override def sub(vector: Vector3fc): Location =
    sub(vector.x, vector.y, vector.z)

  override def add(location: Location): Location =
    add(location.getX, location.getY, location.getZ)

  override def sub(location: Location): Location =
    sub(location.getX, location.getY, location.getZ)

  override def withWorld(world: World): Location = copy(world = world)

  override def withX(x: Float): Location = copy(x = x)

  override def withY(y: Float): Location = copy(y = y)

  override def withZ(z: Float): Location = copy(z = z)

  override def withOrientation(pitch: Float, yaw: Float): OrientedLocation =
    SimpleOrientedLocation(world, x, y, z, pitch, yaw)

  override def toBlockLocation: BlockLocation =
    SimpleBlockLocation(world, x.toInt, y.toInt, z.toInt)

  override def toVector: Vector3f = new Vector3f(x, y, z)
}
