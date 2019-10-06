package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, StickyBlock }

case class Piston(
  location: BlockLocation,
  direction: BlockFace,
  sticky: Boolean,
  extended: Boolean
) extends DirectedBlock with StickyBlock {
  override val kind = BlockType.PISTON

  /* Java interop */

  def withExtended(extended: Boolean): Piston = copy(extended = extended)

  override def withLocation(loc: BlockLocation): Piston = copy(location = loc)
  override def withDirection(facing: BlockFace): Piston = copy(direction = facing)
  override def withSticky(sticky: Boolean): Piston = copy(sticky = sticky)
}
