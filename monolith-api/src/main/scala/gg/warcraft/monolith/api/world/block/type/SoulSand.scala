package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ Block, BlockType }
import gg.warcraft.monolith.api.world.BlockLocation

case class SoulSand(
  location: BlockLocation,
) extends Block {
  override val kind = BlockType.SOUL_SAND

  /* Java interop */

  override def withLocation(loc: BlockLocation): SoulSand = copy(location = loc)
}
