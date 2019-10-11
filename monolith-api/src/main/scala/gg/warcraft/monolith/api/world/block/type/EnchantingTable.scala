package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class EnchantingTable(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.ENCHANTING_TABLE

  override def withLocation(loc: BlockLocation): EnchantingTable = copy(location = loc)
}
