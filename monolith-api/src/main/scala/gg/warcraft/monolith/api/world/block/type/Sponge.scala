package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Sponge(
  location: BlockLocation,
  wet: Boolean
) extends Block {

  /* Java interop */

  override val `type` = BlockType.SPONGE

  def withWet(wet: Boolean): Sponge = copy(wet = wet)

  override def withLocation(loc: BlockLocation): Sponge = copy(location = loc)
}
