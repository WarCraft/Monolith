package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class Jigsaw(
  location: BlockLocation,
  direction: BlockFace
) extends DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.JIGSAW

  override def withLocation(loc: BlockLocation): Jigsaw = copy(location = loc)
  override def withDirection(dir: BlockFace): Jigsaw = copy(direction = dir)
}
