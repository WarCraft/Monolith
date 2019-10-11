package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, ExtendableBlock }

final case class ChorusPlant(
  location: BlockLocation,
  extensions: Set[BlockFace]
) extends ExtendableBlock {

  /* Java interop */

  override val `type` = BlockType.CHORUS_PLANT

  override def withLocation(loc: BlockLocation): ChorusPlant = copy(location = loc)
  override def withExtensions(ext: Set[BlockFace]): ChorusPlant = copy(extensions = ext)
}
