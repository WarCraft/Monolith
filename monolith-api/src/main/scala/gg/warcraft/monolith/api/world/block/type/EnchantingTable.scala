package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class EnchantingTable(location: BlockLocation) extends Block {
  override val kind = BlockType.ENCHANTING_TABLE

  /* Java interop */

  override def withLocation(loc: BlockLocation): EnchantingTable = copy(location = loc)
}
