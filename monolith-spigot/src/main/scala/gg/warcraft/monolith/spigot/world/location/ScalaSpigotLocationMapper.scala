package gg.warcraft.monolith.spigot.world.location

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.location.{BlockLocation, Location, OrientedLocation}
import gg.warcraft.monolith.app.world.location.{SimpleBlockLocation, SimpleOrientedLocation}
import gg.warcraft.monolith.spigot.world.{SpigotBlock, SpigotLocation, SpigotWorldMapper}

class ScalaSpigotLocationMapper @Inject()(
  private val worldMapper: SpigotWorldMapper
) extends SpigotLocationMapper {

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
    location match {
      case oriented: OrientedLocation =>
        val pitch = oriented.getPitch
        val yaw = oriented.getYaw
        new SpigotLocation(world, x, y, z, yaw, pitch)
      case _ =>
        new SpigotLocation(world, x, y, z)
    }
  }

  def map(location: SpigotLocation): OrientedLocation = {
    val world = worldMapper.map(location.getWorld)
    val x = location.getX.toFloat
    val y = location.getY.toFloat
    val z = location.getZ.toFloat
    val pitch = location.getPitch
    val yaw = location.getYaw
    SimpleOrientedLocation(world, x, y, z, pitch, yaw)
  }

  def map(block: SpigotBlock): BlockLocation = {
    val world = worldMapper.map(block.getLocation.getWorld)
    val x = block.getX
    val y = block.getY
    val z = block.getZ
    SimpleBlockLocation(world, x, y, z)
  }
}
