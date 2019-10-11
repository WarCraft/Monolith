package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class LilyPad(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.LILY_PAD

  override def withLocation(loc: BlockLocation): LilyPad = copy(location = loc)
}
