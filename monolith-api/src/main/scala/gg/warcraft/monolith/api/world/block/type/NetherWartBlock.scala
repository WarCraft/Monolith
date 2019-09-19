package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class NetherWartBlock(location: BlockLocation) extends Block {
  override val kind = BlockType.NETHER_WART_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): NetherWartBlock = copy(location = loc)
}
