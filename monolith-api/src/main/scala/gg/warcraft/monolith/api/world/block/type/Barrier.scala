package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Barrier(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.BARRIER

  override def withLocation(loc: BlockLocation): Barrier = copy(location = loc)
}
