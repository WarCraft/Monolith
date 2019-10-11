package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.variant.MobHeadVariant

case class MobHead(
  location: BlockLocation,
  variant: MobHeadVariant,
  direction: Option[BlockFace],
  rotation: Option[BlockRotation]
) extends VariedBlock[MobHeadVariant] with DirectableBlock with RotatableBlock {

  /* Java interop */

  override val `type` = BlockType.MOB_HEAD

  override def withLocation(loc: BlockLocation): MobHead = copy(location = loc)
  override def withVariant(variant: MobHeadVariant): MobHead = copy(variant = variant)
  override def withDirection(dir: Option[BlockFace]): MobHead = copy(direction = dir)
  override def withRotation(rotation: Option[BlockRotation]): MobHead = copy(rotation = rotation)
}
