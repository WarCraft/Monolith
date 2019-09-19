package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Melon(location: BlockLocation) extends Block {
  override val kind = BlockType.MELON

  /* Java interop */

  override def withLocation(loc: BlockLocation): Melon = copy(location = loc)
}
