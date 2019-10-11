package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColoredBlock }

final case class Wool(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {

  /* Java interop */

  override val `type` = BlockType.WOOL

  override def withLocation(loc: BlockLocation): Wool = copy(location = loc)
  override def withColor(color: BlockColor): Wool = copy(color = color)
}
