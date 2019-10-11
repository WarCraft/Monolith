package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class TNT(
  location: BlockLocation,
  unstable: Boolean
) extends Block {

  /* Java interop */

  override val `type` = BlockType.TNT

  def withUnstable(unstable: Boolean): TNT = copy(unstable = unstable)

  override def withLocation(loc: BlockLocation): TNT = copy(location = loc)
}
