package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Fire(location: BlockLocation) extends Block {
  override val solid: Boolean = false

  /* Java interop */

  override val `type` = BlockType.FIRE

  override def withLocation(loc: BlockLocation): Fire = copy(location = loc)
}
