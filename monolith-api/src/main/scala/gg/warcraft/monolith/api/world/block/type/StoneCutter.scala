package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

case class StoneCutter(
  location: BlockLocation,
  direction: BlockFace
) extends DirectedBlock {
  override val kind = BlockType.STONE_CUTTER

  /* Java interop */

  override def withLocation(loc: BlockLocation): StoneCutter = copy(location = loc)
  override def withDirection(facing: BlockFace): StoneCutter = copy(direction = facing)
}
