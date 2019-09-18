package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColorableBlock }

case class Glass(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override val kind = BlockType.GLASS

  /* Java interop */

  override def withLocation(loc: BlockLocation): Glass = copy(location = loc)
  override def withColor(color: Option[BlockColor]): Glass = copy(color = color)
}
