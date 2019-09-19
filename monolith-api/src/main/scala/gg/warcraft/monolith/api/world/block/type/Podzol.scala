package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, SnowableBlock }

case class Podzol(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {
  override val kind = BlockType.PODZOL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Podzol = copy(location = loc)
  override def withSnowy(snowy: Boolean): Podzol = copy(snowy = snowy)
}
