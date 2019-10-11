package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Cobweb(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.COBWEB

  override def withLocation(loc: BlockLocation): Cobweb = copy(location = loc)
}
