package gg.warcraft.monolith.spigot.world

import java.util
import java.util.UUID

import gg.warcraft.monolith.api.item.Item
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{ Location, Sound, SoundCategory, World }
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.service.WorldServerAdapter
import gg.warcraft.monolith.spigot.world.block.SpigotBlockMapper
import javax.inject.Inject

class SpigotWorldService @Inject()(
  private val locationMapper: SpigotLocationMapper,
  private val materialMapper: SpigotMaterialMapper,
  private val worldMapper: SpigotWorldMapper,
  private val blockMapper: SpigotBlockMapper,
) extends WorldServerAdapter {

  override def getBlockAt(world: World, x: Int, y: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world)
    val block = spigotWorld.getBlockAt(x, y, z)
    blockMapper.map(block)
  }

  override def getHighestBlockAt(world: World, x: Int, z: Int): Block = {
    val spigotWorld = worldMapper.map(world)
    val block = spigotWorld.getHighestBlockAt(x, z)
    blockMapper.map(block)
  }

  override def setBlock(block: Block): Unit = {
    val location = locationMapper.map(block.location)
    val spigotBlock = location.getBlock
    val blockState = spigotBlock.getState

    // Update block data
    val newBlockData = blockMapper.map(block)
    blockState.setBlockData(newBlockData)
    blockState.update(/* force */ true, /* physics */ false)

    // Update block state
    val newBlockState = spigotBlock.getState
    blockMapper.map(block, newBlockState)
    newBlockState.update(/* force */ true, /* physics */ false)
  }

  override def dropItemsAt(items: util.List[Item], location: Location): util.List[UUID] = ???

  override def spoofBlock(fakeBlock: Block, playerId: UUID): Unit = ??? // NOTE rename setFakeBlock?

  override def playSound(location: Location, sound: Sound, category: SoundCategory, volume: Float, pitch: Float): Unit = ???

  override def strikeLightning(location: Location, ambient: Boolean): Unit = ???

  override def createExplosion(location: Location, ambient: Boolean): Unit = ???

  override def createArrow(shooterId: UUID, location: Location, direction: Vector3f, speed: Float, spread: Float): UUID = ???
}
