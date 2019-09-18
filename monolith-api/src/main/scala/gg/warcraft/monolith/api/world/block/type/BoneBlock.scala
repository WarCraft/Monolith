package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockOrientation, BlockType, OrientedBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class BoneBlock(
  location: BlockLocation,
  orientation: BlockOrientation
) extends OrientedBlock {
  override val kind = BlockType.BONE_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): BoneBlock = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): BoneBlock = copy(orientation = orientation)
}
