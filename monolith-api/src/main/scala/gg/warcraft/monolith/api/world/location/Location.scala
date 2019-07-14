package gg.warcraft.monolith.api.world.location

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.location.config.LocationConfig
import org.joml.{Vector3f, Vector3fc}

case class Location(
  world: World,
  x: Float,
  y: Float,
  z: Float
) {
  validateWorld(world)
  validateCoordinate(x)
  validateCoordinate(y)
  validateCoordinate(z)

  def getWorld: World = world

  def getX: Float = x

  def getY: Float = y

  def getZ: Float = z

  def add(x: Float, y: Float, z: Float): Location =
    copy(x = this.x + x, y = this.y + y, z = this.z + z)

  def add(vec: Vector3fc): Location =
    add(vec.x, vec.y, vec.z)

  def add(loc: Location): Location =
    add(loc.x, loc.y, loc.z)

  def sub(x: Float, y: Float, z: Float): Location =
    copy(x = this.x - x, y = this.y - y, z = this.z - z)

  def sub(vec: Vector3fc): Location =
    sub(vec.x, vec.y, vec.z)

  def sub(loc: Location): Location =
    sub(loc.x, loc.y, loc.z)

  def toBlockLocation: BlockLocation =
    BlockLocation(world, x.toInt, y.toInt, z.toInt)

  def toVector: Vector3f = new Vector3f(x, y, z)

  def withWorld(world: World): Location = copy(world = world)

  def withX(x: Float): Location = copy(x = x)

  def withY(y: Float): Location = copy(y = y)

  def withZ(z: Float): Location = copy(z = z)
}

object Location {
  def apply(config: LocationConfig) {
    Location(config.world, config.x, config.y, config.z)
  }
}
