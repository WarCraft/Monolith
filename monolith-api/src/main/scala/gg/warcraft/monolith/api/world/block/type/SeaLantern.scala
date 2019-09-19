package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class SeaLantern(location: BlockLocation) extends Block {
  override val kind = BlockType.SEA_LANTERN

  /* Java interop */

  override def withLocation(loc: BlockLocation): SeaLantern = copy(location = loc)
}
