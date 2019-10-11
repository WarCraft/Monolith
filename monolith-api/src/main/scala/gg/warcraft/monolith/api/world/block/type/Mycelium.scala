package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, SnowableBlock }

case class Mycelium(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {

  /* Java interop */

  override val `type` = BlockType.MYCELIUM

  override def withLocation(loc: BlockLocation): Mycelium = copy(location = loc)
  override def withSnowy(snowy: Boolean): Mycelium = copy(snowy = snowy)
}
