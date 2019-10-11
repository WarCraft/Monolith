package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, PowerableBlock, PressurePlateMaterial }

final case class PressurePlate(
  location: BlockLocation,
  material: PressurePlateMaterial,
  powered: Boolean
) extends MaterialBlock[PressurePlateMaterial] with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.PRESSURE_PLATE

  override def withLocation(loc: BlockLocation): PressurePlate = copy(location = loc)
  override def withMaterial(mat: PressurePlateMaterial): PressurePlate = copy(material = mat)
  override def withPowered(powered: Boolean): PressurePlate = copy(powered = powered)
}
