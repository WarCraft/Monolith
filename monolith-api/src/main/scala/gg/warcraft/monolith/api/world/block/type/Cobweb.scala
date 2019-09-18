package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Cobweb(location: BlockLocation) extends Block {
  override val kind = BlockType.COBWEB

  /* Java interop */

  override def withLocation(loc: BlockLocation): Cobweb = copy(location = loc)
}
