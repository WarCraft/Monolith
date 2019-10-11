package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Clay(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.CLAY

  override def withLocation(loc: BlockLocation): Clay = copy(location = loc)
}
