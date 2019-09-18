package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class FletchingTable(location: BlockLocation) extends Block {
  override val kind = BlockType.FLETCHING_TABLE

  /* Java interop */

  override def withLocation(loc: BlockLocation): FletchingTable = copy(location = loc)
}
