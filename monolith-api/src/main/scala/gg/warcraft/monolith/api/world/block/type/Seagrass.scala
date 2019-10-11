package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BisectedBlock, BlockBisection, BlockType }

case class Seagrass(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {

  /* Java interop */

  override val `type` = BlockType.SEAGRASS

  def withTall(tall: Boolean): Seagrass = copy(tall = tall)

  override def withLocation(loc: BlockLocation): Seagrass = copy(location = loc)
  override def withSection(section: BlockBisection): Seagrass = copy(section = section)
}
