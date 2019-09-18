package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockColor, BlockType, ColoredBlock }

case class ConcretePowder(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override val kind = BlockType.CONCRETE_POWDER

  /* Java interop */

  override def withLocation(loc: BlockLocation): ConcretePowder = copy(location = loc)
  override def withColor(color: BlockColor): ConcretePowder = copy(color = color)
}
