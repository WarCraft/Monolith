package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.StructureBlockVariant

case class StructureBlock(
  location: BlockLocation,
  variant: StructureBlockVariant
) extends VariedBlock[StructureBlockVariant] {
  override val kind = BlockType.STRUCTURE_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): StructureBlock = copy(location = loc)
  override def withVariant(variant: StructureBlockVariant): StructureBlock = copy(variant = variant)
}
