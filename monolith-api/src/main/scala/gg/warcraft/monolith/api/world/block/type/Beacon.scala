package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Beacon(location: BlockLocation) extends Block {
  override val kind = BlockType.BEACON

  /* Java interop */

  override def withLocation(loc: BlockLocation): Beacon = copy(location = loc)
}
