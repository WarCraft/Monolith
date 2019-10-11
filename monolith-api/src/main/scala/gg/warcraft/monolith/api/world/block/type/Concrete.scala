package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColoredBlock }

case class Concrete(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {

  /* Java interop */

  override val `type` = BlockType.CONCRETE

  override def withLocation(loc: BlockLocation): Concrete = copy(location = loc)
  override def withColor(color: BlockColor): Concrete = copy(color = color)
}
