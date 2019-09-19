package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Sponge(
  location: BlockLocation,
  wet: Boolean
) extends Block {
  override val kind = BlockType.SPONGE

  /* Java interop */

  def withWet(wet: Boolean): Sponge = copy(wet = wet)

  override def withLocation(loc: BlockLocation): Sponge = copy(location = loc)
}
