package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BisectedBlock, BlockBisection, BlockType }

case class Grass(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {
  override val kind = BlockType.GRASS
  override val solid: Boolean = false

  /* Java interop */

  def withTall(tall: Boolean): Grass = copy(tall = tall)

  override def withLocation(loc: BlockLocation): Grass = copy(location = loc)
  override def withSection(section: BlockBisection): Grass = copy(section = section)
}
