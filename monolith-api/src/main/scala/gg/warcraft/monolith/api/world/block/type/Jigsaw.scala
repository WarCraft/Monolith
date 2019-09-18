package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class Jigsaw(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override val kind = BlockType.JIGSAW

  /* Java interop */

  override def withLocation(loc: BlockLocation): Jigsaw = copy(location = loc)
  override def withFacing(facing: BlockFace): Jigsaw = copy(facing = facing)
}
