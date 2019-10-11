package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.material.SandstoneMaterial
import gg.warcraft.monolith.api.world.block.variant.SandstoneVariant

final case class Sandstone(
  location: BlockLocation,
  material: SandstoneMaterial,
  variant: SandstoneVariant
) extends MaterialBlock[SandstoneMaterial] with VariedBlock[SandstoneVariant] {

  /* Java interop */

  override val `type` = BlockType.SANDSTONE

  override def withLocation(loc: BlockLocation): Sandstone = copy(location = loc)
  override def withMaterial(mat: SandstoneMaterial): Sandstone = copy(material = mat)
  override def withVariant(variant: SandstoneVariant): Sandstone = copy(variant = variant)
}
