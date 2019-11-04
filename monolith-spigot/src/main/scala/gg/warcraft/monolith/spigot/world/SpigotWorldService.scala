package gg.warcraft.monolith.spigot.world

import java.util.UUID

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{ BlockLocation, Location, Sound, SoundCategory, World, WorldService }
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.item.Item
import gg.warcraft.monolith.spigot.world.block.{ SpigotBlock, SpigotBlockMapper }
import javax.inject.Inject
import org.bukkit.Server

import scala.annotation.varargs

class SpigotWorldService @Inject() (
    private val server: Server,
    private val locationMapper: SpigotLocationMapper,
    private val worldMapper: SpigotWorldMapper,
    private val blockMapper: SpigotBlockMapper,
    private val soundMapper: SpigotSoundMapper
) extends WorldService {
  private var spoofBlock: SpigotBlock = null // TODO initialize

  override def getBlock(world: World, x: Int, y: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world)
    val spigotBlock = spigotWorld.getBlockAt(x, y, z)
    blockMapper.map(spigotBlock)
  }

  override def getBlock(location: BlockLocation): Block =
    getBlock(location.world, location.x, location.y, location.z)

  override def getHighestBlock(world: World, x: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world)
    val spigotBlock = spigotWorld.getHighestBlockAt(x, z)
    blockMapper.map(spigotBlock)
  }

  override def getHighestBlock(location: BlockLocation): Block =
    getHighestBlock(location.world, location.x, location.z)

  private def update(spigotBlock: SpigotBlock, to: Block): Unit = {
    val spigotBlockState = spigotBlock.getState

    // Update block data
    val newBlockData = blockMapper.map(to)
    spigotBlockState.setBlockData(newBlockData)
    spigotBlockState.update( /* force */ true, /* physics */ false)

    // Update block state
    val newSpigotBlockState = spigotBlock.getState
    blockMapper.map(to, newSpigotBlockState)
    newSpigotBlockState.update( /* force */ true, /* physics */ false)
  }

  override def setBlock(location: BlockLocation, block: Block): Unit = {
    val spigotLocation = locationMapper.map(block.location)
    update(spigotLocation.getBlock, block)
  }

  override def setBlockType(location: BlockLocation, `type`: BlockType): Unit = {
    val spigotLocation = locationMapper.map(location)
    val spigotType = null // TODO map BlockType to Material
    spigotLocation.getBlock.setType(spigotType)
  }

  override def updateBlock(block: Block): Unit =
    setBlock(block.location, block)

  override def spoofBlock(block: Block, playerId: UUID): Unit = {
    val spigotPlayer = server.getPlayer(playerId)
    if (spigotPlayer != null) {
      val spigotLocation = locationMapper.map(block.location)
      update(spoofBlock, block)
      spigotPlayer.sendBlockChange(spigotLocation, spoofBlock.getBlockData)
    }
  }

  @varargs override def dropItems(location: Location, items: Item*): Unit = {
    throw new IllegalArgumentException // TODO
  }

  override def playSound(
      location: Location,
      sound: Sound,
      category: SoundCategory,
      volume: Float,
      pitch: Float
  ): Unit = {
    val spigotLocation = locationMapper.map(location)
    val spigotWorld = spigotLocation.getWorld
    spigotWorld.playSound(
      spigotLocation,
      soundMapper.map(sound),
      soundMapper.map(category),
      volume,
      pitch
    )
  }

  override def playSound(
      location: Location,
      sound: Sound,
      category: SoundCategory
  ): Unit = playSound(location, sound, category, volume = 1, pitch = 1)

  override def strikeLightning(location: Location, ambient: Boolean): Unit = {
    val spigotLocation = locationMapper.map(location)
    val spigotWorld = spigotLocation.getWorld
    if (ambient) spigotWorld.strikeLightningEffect(spigotLocation)
    else spigotWorld.strikeLightning(spigotLocation)
  }

  override def createExplosion(location: Location, ambient: Boolean): Unit = {
    val spigotWorld = locationMapper.map(location).getWorld
    val Vector3f(x, y, z) = location.translation
    val strength = if (ambient) 0 else 1
    spigotWorld.createExplosion(x, y, z, strength, !ambient, !ambient)
  }

  override def createArrow(
      shooterId: UUID,
      location: Location,
      direction: Vector3f,
      speed: Float,
      spread: Float
  ): UUID = {
    val spigotLocation = locationMapper.map(location)
    val spigotWorld = spigotLocation.getWorld

    throw new IllegalStateException()
    /*
    org.bukkit.Location spigotLocation = locationMapper.map(location);
        org.bukkit.World spigotWorld = spigotLocation.getWorld();
        Vector spigotDirection = new Vector(direction.x(), direction.y(), direction.z());
        Arrow arrow = spigotWorld.spawnArrow(spigotLocation, spigotDirection, speed, spread);

        Entity shooter = server.getEntity(shooterId);
        if (shooter instanceof ProjectileSource) {
            arrow.setShooter((ProjectileSource) shooter);
        }
        return arrow.getUniqueId();
   */
  }
}
