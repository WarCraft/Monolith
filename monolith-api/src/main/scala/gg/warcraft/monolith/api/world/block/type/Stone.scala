package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.material.StoneMaterial
import gg.warcraft.monolith.api.world.block.variant.StoneVariant

final case class Stone(
  location: BlockLocation,
  material: StoneMaterial,
  variant: StoneVariant
) extends MaterialBlock[StoneMaterial] with VariedBlock[StoneVariant] {

  /* Java interop */

  override val `type` = BlockType.STONE

  override def withLocation(loc: BlockLocation): Stone = copy(location = loc)
  override def withMaterial(mat: StoneMaterial): Stone = copy(material = mat)
  override def withVariant(variant: StoneVariant): Stone = copy(variant = variant)
}
