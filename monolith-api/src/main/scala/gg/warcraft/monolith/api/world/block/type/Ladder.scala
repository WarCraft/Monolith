package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, FloodableBlock }

case class Ladder(
  location: BlockLocation,
  direction: BlockFace,
  flooded: Boolean
) extends DirectedBlock with FloodableBlock {
  override val kind = BlockType.LADDER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Ladder = copy(location = loc)
  override def withDirection(facing: BlockFace): Ladder = copy(direction = facing)
  override def withFlooded(flooded: Boolean): Ladder = copy(flooded = flooded)
}
