package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class SnowBlock(location: BlockLocation) extends Block {
  override val kind = BlockType.SNOW_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): SnowBlock = copy(location = loc)
}
