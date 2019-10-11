package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class FletchingTable(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.FLETCHING_TABLE

  override def withLocation(loc: BlockLocation): FletchingTable = copy(location = loc)
}
