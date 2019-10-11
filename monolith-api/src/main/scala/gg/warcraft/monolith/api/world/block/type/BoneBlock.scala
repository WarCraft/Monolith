package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockOrientation, BlockType, OrientedBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class BoneBlock(
  location: BlockLocation,
  orientation: BlockOrientation
) extends OrientedBlock {

  /* Java interop */

  override val `type` = BlockType.BONE_BLOCK

  override def withLocation(loc: BlockLocation): BoneBlock = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): BoneBlock = copy(orientation = orientation)
}
