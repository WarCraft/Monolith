package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}
import gg.warcraft.monolith.api.world.{BlockLocation, Location}
import org.bukkit.{Location => SpigotLocation}
import org.bukkit.block.{Block => SpigotBlock}

class SpigotLocationMapper(
    private implicit val worldMapper: SpigotWorldMapper
) {
  def map(loc: BlockLocation): SpigotLocation = {
    val world = worldMapper.map(loc.world).get
    val Vector3i(x, y, z) = loc.translation
    new SpigotLocation(world, x, y, z)
  }

  def map(loc: Location): SpigotLocation = {
    val world = worldMapper.map(loc.world).get
    val Vector3f(x, y, z) = loc.translation
    new SpigotLocation(world, x, y, z)
  }

  def map(loc: Location, pitchYaw: (Float, Float)): SpigotLocation = {
    val world = worldMapper.map(loc.world).get
    val Vector3f(x, y, z) = loc.translation
    val (pitch, yaw) = pitchYaw
    new SpigotLocation(world, x, y, z, yaw, pitch)
  }

  def map(block: SpigotBlock): BlockLocation = {
    val world = worldMapper.map(block.getLocation.getWorld)
    val translation = Vector3i(block.getX, block.getY, block.getZ)
    BlockLocation(world, translation)
  }

  def map(loc: SpigotLocation): Location = {
    val world = worldMapper.map(loc.getWorld)
    val translation = Vector3f(loc.getX, loc.getY, loc.getZ)
    val rotation = Vector3f(loc.getPitch, loc.getYaw)
    Location(world, translation, rotation)
  }
}
