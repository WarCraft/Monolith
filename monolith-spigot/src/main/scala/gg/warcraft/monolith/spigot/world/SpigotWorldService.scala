package gg.warcraft.monolith.spigot.world

import java.util.UUID

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{
  Location, Sound, SoundCategory, World, WorldService
}
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.spigot.world.block.{
  SpigotBlock, SpigotBlockMapper, SpigotBlockStateMapper, SpigotBlockTypeMapper,
  SpigotBlockVariantMapper
}
import org.bukkit.{Material, Server}
import org.bukkit.block.{BlockFace => SpigotBlockFace}
import org.bukkit.projectiles.ProjectileSource
import org.bukkit.util.Vector

class SpigotWorldService(
    private implicit val server: Server,
    private implicit val worldMapper: SpigotWorldMapper,
    private implicit val locationMapper: SpigotLocationMapper,
    private implicit val blockTypeMapper: SpigotBlockTypeMapper,
    private implicit val blockVariantMapper: SpigotBlockVariantMapper,
    private implicit val blockStateMapper: SpigotBlockStateMapper,
    private implicit val blockMapper: SpigotBlockMapper,
    private implicit val soundMapper: SpigotSoundMapper
) extends WorldService {
  private final val blockVariantPackage =
    "gg.warcraft.monolith.api.world.block.variant"
  private final val blockStatePackage =
    "gg.warcraft.monolith.api.world.block.state"

  override def parseData(data: String): Data = {
    if (data.contains("Variant:")) {
      val Array(enum, value) = data.split(':')
      val clazz = Class.forName(s"$blockVariantPackage.$enum")
      val valueOf = clazz.getMethod("valueOf", classOf[String])
      valueOf.invoke(null, value).asInstanceOf[BlockVariant]
    } else if (data.contains("State:")) {
      val Array(enum, value) = data.split(':')
      val clazz = Class.forName(s"$blockStatePackage.$enum")
      val valueOf = clazz.getMethod("valueOf", classOf[String])
      valueOf.invoke(null, value).asInstanceOf[BlockState]
    } else {
      BlockType.valueOf(data)
    }
  }

  override def getBlock(world: World, x: Int, y: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world).get
    val spigotBlock = spigotWorld.getBlockAt(x, y, z)
    blockMapper.map(spigotBlock).get
  }

  override def getHighestBlock(world: World, x: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world).get
    var spigotBlock = spigotWorld.getHighestBlockAt(x, z)
    do {
      spigotBlock = spigotBlock.getRelative(SpigotBlockFace.UP)
    } while (!spigotBlock.isEmpty)
    do {
      spigotBlock = spigotBlock.getRelative(SpigotBlockFace.DOWN)
    } while (spigotBlock.isEmpty)
    blockMapper.map(spigotBlock).get
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

  override def setBlock(world: World, x: Int, y: Int, z: Int, data: Data): Unit = {
    val spigotWorld = worldMapper.map(world).get
    val spigotLocation = new SpigotLocation(spigotWorld, x, y, z)
    val spigotBlock = spigotLocation.getBlock
    data match {
      case it: BlockType =>
        val material = blockTypeMapper.map(it)
        spigotBlock.setType(Material.AIR)
        spigotBlock.setType(material)
      case it: BlockVariant =>
        // TODO this doesnt set all variants due to block state
        val material = blockVariantMapper.map(it)
        spigotBlock.setType(Material.AIR)
        spigotBlock.setType(material)
      case it: BlockState =>
        val material = blockStateMapper.map(it)
        spigotBlock.setType(Material.AIR)
        spigotBlock.setType(material)
        val blockData = spigotBlock.getBlockData
        blockStateMapper.map(it, blockData)
        spigotBlock.setBlockData(blockData)
    }
  }

  override def setBlock(world: World, x: Int, y: Int, z: Int, block: Block): Unit = {
    val spigotWorld = worldMapper.map(world).get
    val spigotLocation = new SpigotLocation(spigotWorld, x, y, z)
    update(spigotLocation.getBlock, block)
  }

  override def spoofBlock(block: Block, playerId: UUID): Unit = {
    val spigotPlayer = server.getPlayer(playerId)
    if (spigotPlayer != null) {
      val spigotLocation = locationMapper.map(block.location)
      val spigotBlockData = blockMapper.map(block)
      spigotPlayer.sendBlockChange(spigotLocation, spigotBlockData)
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
    val spigotLoc = locationMapper.map(location)
    val spigotWorld = spigotLoc.getWorld
    val spigotDir = new Vector(direction.x, direction.y, direction.z)
    val arrow = spigotWorld.spawnArrow(spigotLoc, spigotDir, speed, spread)

    val shooter = server.getEntity(shooterId)
    shooter match {
      case it: ProjectileSource => arrow.setShooter(it)
      case _                    => ()
    }
    arrow.getUniqueId
  }
}
