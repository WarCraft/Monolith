package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, ExtendableBlock }

case class ChorusPlant(
  location: BlockLocation,
  extensions: Set[BlockFace]
) extends ExtendableBlock {
  override val kind = BlockType.CHORUS_PLANT

  /* Java interop */

  override def withLocation(loc: BlockLocation): ChorusPlant = copy(location = loc)
  override def withExtensions(ext: Set[BlockFace]): ChorusPlant = copy(extensions = ext)
}
