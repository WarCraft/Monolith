package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class CartographyTable(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.CARTOGRAPHY_TABLE

  override def withLocation(loc: BlockLocation): CartographyTable = copy(location = loc)
}
