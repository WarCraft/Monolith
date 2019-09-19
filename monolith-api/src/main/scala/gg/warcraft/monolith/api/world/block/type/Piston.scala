package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, StickyBlock }

case class Piston(
  location: BlockLocation,
  facing: BlockFace,
  sticky: Boolean,
  extended: Boolean
) extends DirectionalBlock with StickyBlock {
  override val kind = BlockType.PISTON

  /* Java interop */

  def withExtended(extended: Boolean): Piston = copy(extended = extended)

  override def withLocation(loc: BlockLocation): Piston = copy(location = loc)
  override def withFacing(facing: BlockFace): Piston = copy(facing = facing)
  override def withSticky(sticky: Boolean): Piston = copy(sticky = sticky)
}
