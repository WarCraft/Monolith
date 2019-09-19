package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColorableBlock }

case class Terracotta(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override val kind = BlockType.TERRACOTTA

  /* Java interop */

  override def withLocation(loc: BlockLocation): Terracotta = copy(location = loc)
  override def withColor(color: Option[BlockColor]): Terracotta = copy(color = color)
}
