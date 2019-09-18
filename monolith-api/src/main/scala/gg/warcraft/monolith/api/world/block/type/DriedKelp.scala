package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class DriedKelp(location: BlockLocation) extends Block {
  override val kind = BlockType.DRIED_KELP

  /* Java interop */

  override def withLocation(loc: BlockLocation): DriedKelp = copy(location = loc)
}
