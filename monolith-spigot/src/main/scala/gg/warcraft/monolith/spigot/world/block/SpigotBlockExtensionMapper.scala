package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block.BlockFace
import javax.inject.Inject
import org.bukkit.block.{ BlockFace => SpigotBlockFace }

import scala.collection.mutable

class SpigotBlockExtensionMapper @Inject()(
    private val blockFaceMapper: SpigotBlockFaceMapper
) {

  def map(facing: util.Set[SpigotBlockFace]): Set[BlockFace] = {
    val extensions = mutable.Set[BlockFace]()
    facing.forEach(face => extensions.add(blockFaceMapper.map(face)))
    extensions.asInstanceOf[Set[BlockFace]]
  }

  def map(facing: Set[BlockFace]): util.Set[SpigotBlockFace] = {
    val extensions = new util.HashSet[SpigotBlockFace]()
    facing.foreach(face => extensions.add(blockFaceMapper.map(face)))
    extensions
  }
}
