package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BisectedBlock, BlockBisection, BlockType }

final case class Fern(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {
  override val solid: Boolean = false

  /* Java interop */

  override val `type` = BlockType.FERN

  def withTall(tall: Boolean): Fern = copy(tall = tall)

  override def withLocation(loc: BlockLocation): Fern = copy(location = loc)
  override def withSection(section: BlockBisection): Fern = copy(section = section)
}
