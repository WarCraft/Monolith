package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

// TODO add wetness state
case class Farmland(location: BlockLocation) extends Block {
  override val kind = BlockType.FARMLAND

  /* Java interop */

  override def withLocation(loc: BlockLocation): Farmland = copy(location = loc)
}
