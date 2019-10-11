package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class SmithingTable(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.SMITHING_TABLE

  override def withLocation(loc: BlockLocation): SmithingTable = copy(location = loc)
}
