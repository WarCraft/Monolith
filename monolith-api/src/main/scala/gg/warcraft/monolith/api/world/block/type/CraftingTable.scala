package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class CraftingTable(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.CRAFTING_TABLE

  override def withLocation(loc: BlockLocation): CraftingTable = copy(location = loc)
}
