package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.StructureBlockVariant

final case class StructureBlock(
  location: BlockLocation,
  variant: StructureBlockVariant
) extends VariedBlock[StructureBlockVariant] {

  /* Java interop */

  override val `type` = BlockType.STRUCTURE_BLOCK

  override def withLocation(loc: BlockLocation): StructureBlock = copy(location = loc)
  override def withVariant(variant: StructureBlockVariant): StructureBlock = copy(variant = variant)
}
