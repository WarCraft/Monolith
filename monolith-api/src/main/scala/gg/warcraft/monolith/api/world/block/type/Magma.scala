package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Magma(location: BlockLocation) extends Block {
  override val kind = BlockType.MAGMA

  /* Java interop */

  override def withLocation(loc: BlockLocation): Magma = copy(location = loc)
}
