package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ Block, BlockType }
import gg.warcraft.monolith.api.world.BlockLocation

case class SoulSand(
  location: BlockLocation,
) extends Block {

  /* Java interop */

  override val `type` = BlockType.SOUL_SAND

  override def withLocation(loc: BlockLocation): SoulSand = copy(location = loc)
}
