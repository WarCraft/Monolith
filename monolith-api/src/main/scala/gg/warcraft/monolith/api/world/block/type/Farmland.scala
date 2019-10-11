package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

// TODO add wetness state
final case class Farmland(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.FARMLAND

  override def withLocation(loc: BlockLocation): Farmland = copy(location = loc)
}
