package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class SlimeBlock(location: BlockLocation) extends Block {
  override val kind = BlockType.SLIME_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): SlimeBlock = copy(location = loc)
}
