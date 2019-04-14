package gg.warcraft.monolith.app.world.location

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.location.{BlockLocation, Location, LocationFactory, OrientedLocation}
import org.joml.Vector3fc

class DefaultLocationFactory extends LocationFactory {

  override def createLocation(
    world: World,
    x: Float, y: Float, z: Float
  ): Location = SimpleLocation(world, x, y, z)

  override def createBlockLocation(
    world: World,
    x: Int, y: Int, z: Int
  ): BlockLocation = SimpleBlockLocation(world, x, y, z)

  override def createOrientedLocation(
    world: World,
    x: Float, y: Float, z: Float,
    pitch: Float, yaw: Float
  ): OrientedLocation = SimpleOrientedLocation(world, x, y, z, pitch, yaw)

  override def createOrientedLocation(
    world: World,
    x: Float, y: Float, z: Float,
    direction: Vector3fc
  ): OrientedLocation = {
    val (pitch, yaw) = directionToOrientation(direction)
    SimpleOrientedLocation(world, x, y, z, pitch, yaw)
  }
}
