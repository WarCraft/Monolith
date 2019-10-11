package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class EndPortal(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.END_PORTAL

  override def withLocation(loc: BlockLocation): EndPortal = copy(location = loc)
}
