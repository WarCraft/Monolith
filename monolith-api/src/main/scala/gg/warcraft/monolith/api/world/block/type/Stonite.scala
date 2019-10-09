package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.material.StoniteMaterial
import gg.warcraft.monolith.api.world.block.variant.StoniteVariant

case class Stonite(
  location: BlockLocation,
  material: StoniteMaterial,
  variant: StoniteVariant
) extends MaterialBlock[StoniteMaterial] with VariedBlock[StoniteVariant] {
  override val kind = BlockType.IGNEOUS_ROCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): Stonite = copy(location = loc)
  override def withMaterial(mat: StoniteMaterial): Stonite = copy(material = mat)
  override def withVariant(mat: StoniteVariant): Stonite = copy(variant = variant)
}
