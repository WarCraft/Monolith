package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Scaffold(location: BlockLocation) extends Block {
  override val kind = BlockType.SCAFFOLD

  /* Java interop */

  override def withLocation(loc: BlockLocation): Scaffold = copy(location = loc)
}
