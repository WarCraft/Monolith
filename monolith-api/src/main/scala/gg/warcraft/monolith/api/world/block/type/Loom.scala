package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock }

case class Loom(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override val kind = BlockType.LOOM

  /* Java interop */

  override def withLocation(loc: BlockLocation): Loom = copy(location = loc)
  override def withFacing(facing: BlockFace): Loom = copy(facing = facing)
}
