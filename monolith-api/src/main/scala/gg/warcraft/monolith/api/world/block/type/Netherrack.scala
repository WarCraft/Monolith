package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Netherrack(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.NETHERRACK

  override def withLocation(loc: BlockLocation): Netherrack = copy(location = loc)
}
