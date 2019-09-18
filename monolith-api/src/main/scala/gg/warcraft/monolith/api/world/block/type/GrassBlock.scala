package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, SnowableBlock }

case class GrassBlock(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {
  override val kind = BlockType.GRASS_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): GrassBlock = copy(location = loc)
  override def withSnowy(snowy: Boolean): GrassBlock = copy(snowy = snowy)
}
