package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Dirt(
  location: BlockLocation,
  coarse: Boolean
) extends Block {
  override val kind = BlockType.DIRT

  /* Java interop */

  def withCoarse(coarse: Boolean): Dirt = copy(coarse = coarse)

  override def withLocation(loc: BlockLocation): Dirt = copy(location = loc)
}
