package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BisectedBlock, BlockBisection, BlockType }

case class Fern(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {
  override val kind = BlockType.FERN
  override val solid: Boolean = false

  /* Java interop */

  def withTall(tall: Boolean): Fern = copy(tall = tall)

  override def withLocation(loc: BlockLocation): Fern = copy(location = loc)
  override def withSection(section: BlockBisection): Fern = copy(section = section)
}
