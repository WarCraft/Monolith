package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

case class Loom(
  location: BlockLocation,
  direction: BlockFace
) extends DirectedBlock {
  override val kind = BlockType.LOOM

  /* Java interop */

  override def withLocation(loc: BlockLocation): Loom = copy(location = loc)
  override def withDirection(facing: BlockFace): Loom = copy(direction = facing)
}
