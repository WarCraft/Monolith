package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, ExtendableBlock }

case class Vine(
  location: BlockLocation,
  extensions: Set[BlockFace]
) extends ExtendableBlock {

  /* Java interop */

  override val `type` = BlockType.VINE

  override def withLocation(loc: BlockLocation): Vine = copy(location = loc)
  override def withExtensions(extensions: Set[BlockFace]): Vine = copy(extensions = extensions)
}
