package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class Jigsaw(
  location: BlockLocation,
  direction: BlockFace
) extends DirectedBlock {
  override val kind = BlockType.JIGSAW

  /* Java interop */

  override def withLocation(loc: BlockLocation): Jigsaw = copy(location = loc)
  override def withDirection(facing: BlockFace): Jigsaw = copy(direction = facing)
}
