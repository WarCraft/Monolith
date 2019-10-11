package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class EndGateway(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.END_GATEWAY

  override def withLocation(loc: BlockLocation): EndGateway = copy(location = loc)
}
