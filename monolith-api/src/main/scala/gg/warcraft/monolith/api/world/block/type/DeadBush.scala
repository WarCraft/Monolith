package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class DeadBush(location: BlockLocation) extends Block {
  override val kind = BlockType.DEAD_BUSH
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): DeadBush = copy(location = loc)
}
