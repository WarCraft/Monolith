package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, SnowableBlock }

case class GrassBlock(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {

  /* Java interop */

  override val `type` = BlockType.GRASS_BLOCK

  override def withLocation(loc: BlockLocation): GrassBlock = copy(location = loc)
  override def withSnowy(snowy: Boolean): GrassBlock = copy(snowy = snowy)
}
