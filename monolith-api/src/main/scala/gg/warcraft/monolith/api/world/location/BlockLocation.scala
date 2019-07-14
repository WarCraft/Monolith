package gg.warcraft.monolith.api.world.location

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.location.config.BlockLocationConfig
import org.joml.{Vector3i, Vector3ic}

case class BlockLocation(
  world: World,
  x: Int,
  y: Int,
  z: Int
) {
  validateWorld(world)
  validateCoordinate(x)
  validateCoordinate(y)
  validateCoordinate(z)

  def getWorld: World = world

  def getX: Int = x

  def getY: Int = y

  def getZ: Int = z

  def add(x: Int, y: Int, z: Int): BlockLocation =
    copy(x = this.x + x, y = this.y + y, z = this.z + z)

  def add(vec: Vector3ic): BlockLocation =
    add(vec.x, vec.y, vec.z)

  def add(loc: BlockLocation): BlockLocation =
    add(loc.x, loc.y, loc.z)

  def sub(x: Int, y: Int, z: Int): BlockLocation =
    copy(x = this.x - x, y = this.y - y, z = this.z - z)

  def sub(vec: Vector3ic): BlockLocation =
    sub(vec.x, vec.y, vec.z)

  def sub(loc: BlockLocation): BlockLocation =
    sub(loc.x, loc.y, loc.z)

  def toLocation: Location = Location(world, x, y, z)

  def toVector: Vector3i = new Vector3i(x, y, z)

  def withWorld(world: World): BlockLocation = copy(world = world)

  def withX(x: Int): BlockLocation = copy(x = x)

  def withY(y: Int): BlockLocation = copy(y = y)

  def withZ(z: Int): BlockLocation = copy(z = z)
}

object BlockLocation {
  def apply(config: BlockLocationConfig) {
    BlockLocation(config.world, config.x, config.y, config.z)
  }
}
