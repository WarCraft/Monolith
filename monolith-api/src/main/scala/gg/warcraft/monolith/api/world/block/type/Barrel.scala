package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, OpenableBlock }

case class Barrel(
  location: BlockLocation,
  direction: BlockFace,
  open: Boolean
) extends DirectedBlock with OpenableBlock {

  /* Java interop */

  override val `type` = BlockType.BARREL

  override def withLocation(loc: BlockLocation): Barrel = copy(location = loc)
  override def withDirection(dir: BlockFace): Barrel = copy(direction = dir)
  override def withOpen(open: Boolean): Barrel = copy(open = open)
}
