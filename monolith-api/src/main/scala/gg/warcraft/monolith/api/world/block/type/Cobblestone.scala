package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Cobblestone(location: BlockLocation) extends Block {
  override val kind = BlockType.COBBLESTONE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Cobblestone = copy(location = loc)
}
