package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColorableBlock }

case class ShulkerBox(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override val kind = BlockType.SHULKER_BOX

  /* Java interop */

  override def withLocation(loc: BlockLocation): ShulkerBox = copy(location = loc)
  override def withColor(color: Option[BlockColor]): ShulkerBox = copy(color = color)
}
