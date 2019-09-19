package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, FloodableBlock }

case class Ladder(
  location: BlockLocation,
  facing: BlockFace,
  flooded: Boolean
) extends DirectionalBlock with FloodableBlock {
  override val kind = BlockType.LADDER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Ladder = copy(location = loc)
  override def withFacing(facing: BlockFace): Ladder = copy(facing = facing)
  override def withFlooded(flooded: Boolean): Ladder = copy(flooded = flooded)
}
