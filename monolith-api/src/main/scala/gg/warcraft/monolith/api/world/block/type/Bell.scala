package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock }

// TODO add attachment val (NOTE this one is different for Bells from attachedTo)
case class Bell(
  location: BlockLocation,
  facing: BlockFace,
) extends DirectionalBlock {
  override val kind = BlockType.BELL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Bell = copy(location = loc)
  override def withFacing(facing: BlockFace): Bell = copy(facing = facing)
}
