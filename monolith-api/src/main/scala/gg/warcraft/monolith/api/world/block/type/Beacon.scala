package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Beacon(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.BEACON

  override def withLocation(loc: BlockLocation): Beacon = copy(location = loc)
}
