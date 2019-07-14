package random

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.location.{BlockLocation, Location, Orientation}

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

  def blockLocation(): BlockLocation = {
    val world = this.world()
    val (x, y, z) = blockCoordinates()
    BlockLocation(world, x, y, z)
  }

  def locationCoordinates(): (Float, Float, Float) = {
    val (x, y, z) = blockCoordinates()
    (x * Random.nextFloat(), y * Random.nextFloat(), z * Random.nextFloat())
  }

  def location(): Location = {
    val world = this.world()
    val (x, y, z) = locationCoordinates()
    Location(world, x, y, z)
  }

  def pitchYaw(): (Float, Float) = {
    (0, 0)
  }

  def simpleLocationWithOrientation(): (Location, Orientation) = {
    val world = this.world()
    val (x, y, z) = locationCoordinates()
    val (pitch, yaw) = pitchYaw()
    val location = Location(world, x, y, z)
    val orientation = Orientation(pitch, yaw)
    (location, orientation)
  }
}
