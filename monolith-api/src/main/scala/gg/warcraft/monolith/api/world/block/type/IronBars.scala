package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, ExtendableBlock, FloodableBlock }

case class IronBars(
  location: BlockLocation,
  extensions: Set[BlockFace],
  flooded: Boolean
) extends ExtendableBlock with FloodableBlock {

  /* Java interop */

  override val `type` = BlockType.IRON_BARS

  override def withLocation(loc: BlockLocation): IronBars = copy(location = loc)
  override def withExtensions(ext: Set[BlockFace]): IronBars = copy(extensions = ext)
  override def withFlooded(flooded: Boolean): IronBars = copy(flooded = flooded)
}
