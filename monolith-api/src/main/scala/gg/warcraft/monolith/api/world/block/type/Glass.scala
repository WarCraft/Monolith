package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColorableBlock }

final case class Glass(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {

  /* Java interop */

  override val `type` = BlockType.GLASS

  override def withLocation(loc: BlockLocation): Glass = copy(location = loc)
  override def withColor(color: Option[BlockColor]): Glass = copy(color = color)
}
