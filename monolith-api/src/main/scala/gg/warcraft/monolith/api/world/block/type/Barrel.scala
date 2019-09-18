package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, OpenableBlock }

case class Barrel(
  location: BlockLocation,
  facing: BlockFace,
  open: Boolean
) extends DirectionalBlock with OpenableBlock {
  override val kind = BlockType.BARREL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Barrel = copy(location = loc)
  override def withFacing(facing: BlockFace): Barrel = copy(facing = facing)
  override def withOpen(open: Boolean): Barrel = copy(open = open)
}
