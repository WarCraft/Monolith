package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Melon(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.MELON

  override def withLocation(loc: BlockLocation): Melon = copy(location = loc)
}
