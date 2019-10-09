package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.material.IgneousRockMaterial
import gg.warcraft.monolith.api.world.block.variant.IgneousRockVariant

case class IgneousRock(
  location: BlockLocation,
  material: IgneousRockMaterial,
  variant: IgneousRockVariant
) extends MaterialBlock[IgneousRockMaterial] with VariedBlock[IgneousRockVariant] {
  override val kind = BlockType.IGNEOUS_ROCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): IgneousRock = copy(location = loc)
  override def withMaterial(mat: IgneousRockMaterial): IgneousRock = copy(material = mat)
  override def withVariant(mat: IgneousRockVariant): IgneousRock = copy(variant = variant)
}
