package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Scaffold(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.SCAFFOLD

  override def withLocation(loc: BlockLocation): Scaffold = copy(location = loc)
}
