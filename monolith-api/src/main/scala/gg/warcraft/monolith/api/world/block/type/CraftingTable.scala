package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class CraftingTable(location: BlockLocation) extends Block {
  override val kind = BlockType.CRAFTING_TABLE

  /* Java interop */

  override def withLocation(loc: BlockLocation): CraftingTable = copy(location = loc)
}
