package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class EndPortal(location: BlockLocation) extends Block {
  override val kind = BlockType.END_PORTAL

  /* Java interop */

  override def withLocation(loc: BlockLocation): EndPortal = copy(location = loc)
}
