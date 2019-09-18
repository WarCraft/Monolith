package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class CartographyTable(location: BlockLocation) extends Block {
  override val kind = BlockType.CARTOGRAPHY_TABLE

  /* Java interop */

  override def withLocation(loc: BlockLocation): CartographyTable = copy(location = loc)
}
