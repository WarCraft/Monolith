package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Bookshelf(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.BOOKSHELF

  override def withLocation(loc: BlockLocation): Bookshelf = copy(location = loc)
}
