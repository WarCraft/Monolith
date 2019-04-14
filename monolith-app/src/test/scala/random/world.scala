package random

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.app.world.location.{SimpleBlockLocation, SimpleLocation, SimpleOrientedLocation}

import scala.util.Random

object world {
  private val worlds = List(World.OVERWORLD, World.THE_NETHER, World.THE_END)
  private val maxCoordinate = 1000
  private val minPitch = 0
  private val maxPitch = 0
  private val minYaw = 0
  private val maxYaw = 0

  def world(): World = worlds(Random.nextInt(worlds.size))

  def blockCoordinates(): (Int, Int, Int) = (
    Random.nextInt(maxCoordinate) - maxCoordinate,
    Random.nextInt(maxCoordinate) - maxCoordinate,
    Random.nextInt(maxCoordinate) - maxCoordinate
  )

  def simpleBlockLocation(): SimpleBlockLocation = {
    val world = this.world()
    val (x, y, z) = blockCoordinates()
    SimpleBlockLocation(world, x, y, z)
  }

  def locationCoordinates(): (Float, Float, Float) = {
    val (x, y, z) = blockCoordinates()
    (x * Random.nextFloat(), y * Random.nextFloat(), z * Random.nextFloat())
  }

  def simpleLocation(): SimpleLocation = {
    val world = this.world()
    val (x, y, z) = locationCoordinates()
    SimpleLocation(world, x, y, z)
  }

  def orientation(): (Float, Float) = {
    (0, 0) // TODO
  }

  def simpleOrientedLocation(): SimpleOrientedLocation = {
    val world = this.world()
    val (x, y, z) = locationCoordinates()
    val (pitch, yaw) = orientation()
    SimpleOrientedLocation(world, x, y, z, pitch, yaw)
  }
}
