package gg.warcraft.monolith.spigot.world

import com.google.inject.Inject
import gg.warcraft.monolith.api.math.{ Vector3f, Vector3i }
import gg.warcraft.monolith.api.world.{ BlockLocation, Location }
import org.bukkit.{ Location => SpigotLocation }
import org.bukkit.block.{ Block => SpigotBlock }

class SpigotLocationMapper @Inject()(
  private val worldMapper: SpigotWorldMapper
) {

  def map(location: BlockLocation): SpigotLocation = {
    val world = worldMapper.map(location.world)
    val Vector3i(x, y, z) = location.translation
    new SpigotLocation(world, x, y, z)
  }

  def map(location: Location): SpigotLocation = {
    val world = worldMapper.map(location.world)
    val Vector3f(x, y, z) = location.translation
    new SpigotLocation(world, x, y, z)
  }

  def map(location: Location, pitchYaw: (Float, Float)): SpigotLocation = {
    val world = worldMapper.map(location.world)
    val Vector3f(x, y, z) = location.translation
    val pitch = pitchYaw._1
    val yaw = pitchYaw._2
    new SpigotLocation(world, x, y, z, yaw, pitch)
  }

  def map(block: SpigotBlock): BlockLocation = {
    val world = worldMapper.map(block.getLocation.getWorld)
    val x = block.getX
    val y = block.getY
    val z = block.getZ
    BlockLocation(world, Vector3i(x, y, z))
  }

  def map(location: SpigotLocation): Location = {
    val world = worldMapper.map(location.getWorld)
    val x = location.getX.toFloat
    val y = location.getY.toFloat
    val z = location.getZ.toFloat
    val pitch = location.getPitch
    val yaw = location.getYaw
    Location(world, Vector3f(x, y, z), Vector3f(pitch, yaw))
  }
}
