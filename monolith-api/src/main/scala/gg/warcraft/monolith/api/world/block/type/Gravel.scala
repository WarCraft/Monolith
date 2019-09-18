package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ Block, BlockType }
import gg.warcraft.monolith.api.world.BlockLocation

case class Gravel(location: BlockLocation) extends Block {
  override val kind = BlockType.GRAVEL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Gravel = copy(location = loc)
}
