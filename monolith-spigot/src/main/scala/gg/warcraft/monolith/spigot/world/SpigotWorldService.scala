package gg.warcraft.monolith.spigot.world

import java.util.UUID

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{Location, Sound, SoundCategory, World}
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.service.WorldServerAdapter
import gg.warcraft.monolith.spigot.world.block.{
  SpigotBlock,
  SpigotBlockMapper,
  SpigotBlockMaterialMapper
}
import javax.inject.Inject
import org.bukkit.Server

class SpigotWorldService @Inject()(
    private val server: Server,
    private val locationMapper: SpigotLocationMapper,
    private val materialMapper: SpigotBlockMaterialMapper,
    private val worldMapper: SpigotWorldMapper,
    private val blockMapper: SpigotBlockMapper,
    private val soundMapper: SpigotSoundMapper
) extends WorldServerAdapter {

  private var spoofBlock: SpigotBlock = null // TODO initialize

  override def getBlockAt(world: World, x: Int, y: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world)
    val spigotBlock = spigotWorld.getBlockAt(x, y, z)
    blockMapper.map(spigotBlock)
  }

  override def getHighestBlockAt(world: World, x: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world)
    val spigotBlock = spigotWorld.getHighestBlockAt(x, z)
    blockMapper.map(spigotBlock)
  }

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

  override def updateBlock(block: Block): Unit = {
    val spigotLocation = locationMapper.map(block.location)
    update(spigotLocation.getBlock, block)
  }

  override def spoofBlock(block: Block, playerId: UUID): Unit = {
    val spigotPlayer = server.getPlayer(playerId)
    if (spigotPlayer != null) {
      val spigotLocation = locationMapper.map(block.location)
      update(spoofBlock, block)
      spigotPlayer.sendBlockChange(spigotLocation, spoofBlock.getBlockData)
    }
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

  // TODO add dropItems method
}
