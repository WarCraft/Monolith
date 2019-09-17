package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockFace
import org.bukkit.block.data.MultipleFacing

import scala.collection.mutable

class SpigotBlockExtensionMapper(
  private val blockFaceMapper: SpigotBlockFaceMapper
) {

  def map(facing: MultipleFacing): Set[BlockFace] = {
    val extensions = mutable.Set[BlockFace]()
    facing.getFaces.forEach(face => extensions.add(blockFaceMapper.map(face)))
    extensions.asInstanceOf[Set[BlockFace]]
  }

  def map(facing: Set[BlockFace]): MultipleFacing = {
    null
  }
}
