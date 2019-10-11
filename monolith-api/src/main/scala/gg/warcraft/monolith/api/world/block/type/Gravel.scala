package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ Block, BlockType }
import gg.warcraft.monolith.api.world.BlockLocation

final case class Gravel(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.GRAVEL

  override def withLocation(loc: BlockLocation): Gravel = copy(location = loc)
}
