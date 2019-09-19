package gg.warcraft.monolith.spigot.world.block

import java.util.{ HashSet => JavaHashSet, Set => JavaSet }

import gg.warcraft.monolith.api.world.block.BlockFace
import org.bukkit.block.{ BlockFace => SpigotBlockFace }

import scala.collection.mutable

class SpigotBlockExtensionMapper(
  private val blockFaceMapper: SpigotBlockFaceMapper
) {

  def map(facing: JavaSet[SpigotBlockFace]): Set[BlockFace] = {
    val extensions = mutable.Set[BlockFace]()
    facing.forEach(face => extensions.add(blockFaceMapper.map(face)))
    extensions.asInstanceOf[Set[BlockFace]]
  }

  def map(facing: Set[BlockFace]): JavaSet[SpigotBlockFace] = {
    val extensions = new JavaHashSet[SpigotBlockFace]()
    facing.foreach(face => extensions.add(blockFaceMapper.map(face)))
    extensions
  }
}
