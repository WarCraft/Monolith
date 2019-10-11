package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Magma(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.MAGMA

  override def withLocation(loc: BlockLocation): Magma = copy(location = loc)
}
