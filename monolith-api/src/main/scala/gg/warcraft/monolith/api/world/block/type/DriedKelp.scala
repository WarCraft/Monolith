package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class DriedKelp(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.DRIED_KELP

  override def withLocation(loc: BlockLocation): DriedKelp = copy(location = loc)
}
