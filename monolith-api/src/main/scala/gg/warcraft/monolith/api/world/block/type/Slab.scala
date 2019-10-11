package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Slab(
  location: BlockLocation,
  material: SlabMaterial,
  variant: Option[SlabVariant],
  section: BlockBisection
) extends MaterialBlock[SlabMaterial] with VariableBlock[SlabVariant] with BisectedBlock {

  /* Java interop */

  override val `type` = BlockType.SLAB

  override def withLocation(loc: BlockLocation): Slab = copy(location = loc)
  override def withMaterial(mat: SlabMaterial): Slab = copy(material = mat)
  override def withVariant(variant: Option[SlabVariant]): Slab = copy(variant = variant)
  override def withSection(section: BlockBisection): Slab = copy(section = section)
}
