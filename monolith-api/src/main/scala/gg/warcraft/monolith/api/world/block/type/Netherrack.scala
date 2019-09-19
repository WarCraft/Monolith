package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Netherrack(location: BlockLocation) extends Block {
  override val kind = BlockType.NETHERRACK

  /* Java interop */

  override def withLocation(loc: BlockLocation): Netherrack = copy(location = loc)
}
