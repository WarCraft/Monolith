package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class LilyPad(location: BlockLocation) extends Block {
  override val kind = BlockType.LILY_PAD

  /* Java interop */

  override def withLocation(loc: BlockLocation): LilyPad = copy(location = loc)
}
