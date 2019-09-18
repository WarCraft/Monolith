package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Bookshelf(location: BlockLocation) extends Block {
  override val kind = BlockType.BOOKSHELF

  /* Java interop */

  override def withLocation(loc: BlockLocation): Bookshelf = copy(location = loc)
}
