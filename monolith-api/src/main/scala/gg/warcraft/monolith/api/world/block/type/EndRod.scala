package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock }

case class EndRod(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override val kind = BlockType.END_ROD

  /* Java interop */

  override def withLocation(loc: BlockLocation): EndRod = copy(location = loc)
  override def withFacing(facing: BlockFace): EndRod = copy(facing = facing)
}
