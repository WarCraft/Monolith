package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class EndGateway(location: BlockLocation) extends Block {
  override val kind = BlockType.END_GATEWAY

  /* Java interop */

  override def withLocation(loc: BlockLocation): EndGateway = copy(location = loc)
}
