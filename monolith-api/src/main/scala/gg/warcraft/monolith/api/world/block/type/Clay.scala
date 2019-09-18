package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Clay(location: BlockLocation) extends Block {
  override val kind = BlockType.CLAY

  /* Java interop */

  override def withLocation(loc: BlockLocation): Clay = copy(location = loc)
}
