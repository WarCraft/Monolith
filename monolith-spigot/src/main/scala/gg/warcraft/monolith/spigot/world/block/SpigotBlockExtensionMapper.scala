package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block.BlockFace
import javax.inject.Inject
import org.bukkit.block.{BlockFace => SpigotBlockFace}

import scala.jdk.CollectionConverters._

class SpigotBlockExtensionMapper @Inject() (
    private val blockFaceMapper: SpigotBlockFaceMapper
) {
  // TODO move these 2 methods to BlockFaceMapper?
  def map(facing: util.Set[SpigotBlockFace]): Set[BlockFace] =
    facing.asScala.map(blockFaceMapper.map).toSet

  def map(facing: Set[BlockFace]): util.Set[SpigotBlockFace] =
    facing.map(blockFaceMapper.map).asJava
}
