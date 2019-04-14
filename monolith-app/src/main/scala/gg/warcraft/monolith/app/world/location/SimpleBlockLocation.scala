package gg.warcraft.monolith.app.world.location

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.location.{BlockLocation, Location}
import org.joml.{Vector3i, Vector3ic}

case class SimpleBlockLocation(
  world: World,
  x: Int,
  y: Int,
  z: Int
) extends BlockLocation {
  validateWorld(world)

  override def getWorld: World = world

  override def getX: Int = x

  override def getY: Int = y

  override def getZ: Int = z

  override def add(x: Int, y: Int, z: Int): BlockLocation =
    SimpleBlockLocation(world, this.x + x, this.y + y, this.z + z)

  override def sub(x: Int, y: Int, z: Int): BlockLocation =
    SimpleBlockLocation(world, this.x - x, this.y - y, this.z - z)

  override def add(vector: Vector3ic): BlockLocation =
    add(vector.x, vector.y, vector.z)

  override def sub(vector: Vector3ic): BlockLocation =
    sub(vector.x, vector.y, vector.z)

  override def add(location: BlockLocation): BlockLocation =
    add(location.getX, location.getY, location.getZ)

  override def sub(location: BlockLocation): BlockLocation =
    sub(location.getX, location.getY, location.getZ)

  override def withWorld(world: World): BlockLocation = copy(world = world)

  override def withX(x: Int): BlockLocation = copy(x = x)

  override def withY(y: Int): BlockLocation = copy(y = y)

  override def withZ(z: Int): BlockLocation = copy(z = z)

  override def toVector: Vector3i = new Vector3i(x, y, z)

  override def toLocation: Location = SimpleLocation(world, x, y, z)
}
