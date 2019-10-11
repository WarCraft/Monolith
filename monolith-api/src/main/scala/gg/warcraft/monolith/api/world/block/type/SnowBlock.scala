package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class SnowBlock(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.SNOW_BLOCK

  override def withLocation(loc: BlockLocation): SnowBlock = copy(location = loc)
}
