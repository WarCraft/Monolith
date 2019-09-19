package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, ExtendableBlock }

case class Vine(
  location: BlockLocation,
  extensions: Set[BlockFace]
) extends ExtendableBlock {
  override val kind = BlockType.VINE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Vine = copy(location = loc)
  override def withExtensions(extensions: Set[BlockFace]): Vine = copy(extensions = extensions)
}
