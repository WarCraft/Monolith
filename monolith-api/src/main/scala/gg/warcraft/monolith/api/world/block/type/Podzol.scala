package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, SnowableBlock }

final case class Podzol(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {

  /* Java interop */

  override val `type` = BlockType.PODZOL

  override def withLocation(loc: BlockLocation): Podzol = copy(location = loc)
  override def withSnowy(snowy: Boolean): Podzol = copy(snowy = snowy)
}
