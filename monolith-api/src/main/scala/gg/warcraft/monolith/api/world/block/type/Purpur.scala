package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Purpur(location: BlockLocation) extends Block {
  override val kind = BlockType.PURPUR

  /* Java interop */

  override def withLocation(loc: BlockLocation): Purpur = copy(location = loc)
}
