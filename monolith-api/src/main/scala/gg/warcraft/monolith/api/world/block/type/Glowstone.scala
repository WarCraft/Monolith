package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Glowstone(location: BlockLocation) extends Block {
  override val kind = BlockType.GLOWSTONE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Glowstone = copy(location = loc)
}
