package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Fire(location: BlockLocation) extends Block {
  override val kind = BlockType.FIRE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Fire = copy(location = loc)
}
