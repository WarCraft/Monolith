package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColoredBlock }

case class Wool(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override val kind = BlockType.WOOL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Wool = copy(location = loc)
  override def withColor(color: BlockColor): Wool = copy(color = color)
}
