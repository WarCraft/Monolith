package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{Block, BlockType}

final case class Purpur(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.PURPUR

  override def withLocation(loc: BlockLocation): Purpur = copy(location = loc)
}
