package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Glowstone(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.GLOWSTONE

  override def withLocation(loc: BlockLocation): Glowstone = copy(location = loc)
}
