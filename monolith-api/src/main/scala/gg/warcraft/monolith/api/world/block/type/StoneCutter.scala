package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock }

case class StoneCutter(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override val kind = BlockType.STONE_CUTTER

  /* Java interop */

  override def withLocation(loc: BlockLocation): StoneCutter = copy(location = loc)
  override def withFacing(facing: BlockFace): StoneCutter = copy(facing = facing)
}
