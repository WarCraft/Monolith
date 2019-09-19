package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material.MobHeadMaterial

case class MobHead(
  location: BlockLocation,
  material: MobHeadMaterial,
  facing: Option[BlockFace],
  rotation: Option[BlockRotation]
) extends MaterialBlock[MobHeadMaterial] with DirectableBlock with RotatableBlock {
  override val kind = BlockType.MOB_HEAD

  /* Java interop */

  override def withLocation(loc: BlockLocation): MobHead = copy(location = loc)
  override def withMaterial(mat: MobHeadMaterial): MobHead = copy(material = mat)
  override def withFacing(facing: Option[BlockFace]): MobHead = copy(facing = facing)
  override def withRotation(rotation: Option[BlockRotation]): MobHead = copy(rotation = rotation)
}
