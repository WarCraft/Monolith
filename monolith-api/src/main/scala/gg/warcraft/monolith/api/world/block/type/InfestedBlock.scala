package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, InfestedMaterial, InfestedVariant, MaterialBlock, VariedBlock }

case class InfestedBlock(
  location: BlockLocation,
  material: InfestedMaterial,
  variant: InfestedVariant
) extends MaterialBlock[InfestedMaterial] with VariedBlock[InfestedVariant] {
  override val kind = BlockType.INFESTED_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): InfestedBlock = copy(location = loc)
  override def withMaterial(mat: InfestedMaterial): InfestedBlock = copy(material = mat)
  override def withVariant(variant: InfestedVariant): InfestedBlock = copy(variant = variant)
}
