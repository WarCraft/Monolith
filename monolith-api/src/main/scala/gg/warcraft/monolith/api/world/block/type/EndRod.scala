package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

case class EndRod(
  location: BlockLocation,
  direction: BlockFace
) extends DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.END_ROD

  override def withLocation(loc: BlockLocation): EndRod = copy(location = loc)
  override def withDirection(dir: BlockFace): EndRod = copy(direction = dir)
}
