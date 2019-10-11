package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

case class StoneCutter(
  location: BlockLocation,
  direction: BlockFace
) extends DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.STONE_CUTTER

  override def withLocation(loc: BlockLocation): StoneCutter = copy(location = loc)
  override def withDirection(dir: BlockFace): StoneCutter = copy(direction = dir)
}
