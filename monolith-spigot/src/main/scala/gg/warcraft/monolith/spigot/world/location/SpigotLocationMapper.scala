package gg.warcraft.monolith.spigot.world.location

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.location.{BlockLocation, Location, Orientation}
import gg.warcraft.monolith.spigot.world.{SpigotBlock, SpigotLocation, SpigotWorldMapper}

class SpigotLocationMapper @Inject()(
  private val worldMapper: SpigotWorldMapper
) {

  def map(location: BlockLocation): SpigotLocation = {
    val world = worldMapper.map(location.getWorld)
    val x = location.getX
    val y = location.getY
    val z = location.getZ
    new SpigotLocation(world, x, y, z)
  }

  def map(location: Location): SpigotLocation = {
    val world = worldMapper.map(location.getWorld)
    val x = location.getX
    val y = location.getY
    val z = location.getZ
    new SpigotLocation(world, x, y, z)
  }

  def map(location: Location, orientation: Orientation): SpigotLocation = {
    val world = worldMapper.map(location.getWorld)
    val x = location.getX
    val y = location.getY
    val z = location.getZ
    val pitch = orientation.pitch
    val yaw = orientation.yaw
    new SpigotLocation(world, x, y, z, yaw, pitch)
  }

  def map(block: SpigotBlock): BlockLocation = {
    val world = worldMapper.map(block.getLocation.getWorld)
    val x = block.getX
    val y = block.getY
    val z = block.getZ
    BlockLocation(world, x, y, z)
  }

  def map(location: SpigotLocation): Location = {
    val world = worldMapper.map(location.getWorld)
    val x = location.getX.toFloat
    val y = location.getY.toFloat
    val z = location.getZ.toFloat
    val pitch = location.getPitch
    val yaw = location.getYaw
    Location(world, x, y, z)
  }

  def mapWithOrientation(location: SpigotLocation): (Location, Orientation) = {
    val world = worldMapper.map(location.getWorld)
    val x = location.getX.toFloat
    val y = location.getY.toFloat
    val z = location.getZ.toFloat
    val pitch = location.getPitch
    val yaw = location.getYaw
    (Location(world, x, y, z), Orientation(pitch, yaw))
  }
}
