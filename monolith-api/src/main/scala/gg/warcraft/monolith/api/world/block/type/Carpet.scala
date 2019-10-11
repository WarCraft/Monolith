package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColoredBlock }
import gg.warcraft.monolith.api.world.BlockLocation

final case class Carpet(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {

  /* Java interop */

  override val `type` = BlockType.CARPET

  override def withLocation(loc: BlockLocation): Carpet = copy(location = loc)
  override def withColor(color: BlockColor): Carpet = copy(color = color)
}
