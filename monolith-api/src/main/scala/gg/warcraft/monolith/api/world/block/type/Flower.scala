package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BisectedBlock, BlockBisection, BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.FlowerMaterial

case class Flower(
  location: BlockLocation,
  material: FlowerMaterial,
  section: BlockBisection,
  tall: Boolean
) extends MaterialBlock[FlowerMaterial] with BisectedBlock {
  override val kind = BlockType.FLOWER
  override val solid: Boolean = false

  /* Java interop */

  def withTall(tall: Boolean): Flower = copy(tall = tall)

  override def withLocation(loc: BlockLocation): Flower = copy(location = loc)
  override def withMaterial(mat: FlowerMaterial): Flower = copy(material = mat)
  override def withSection(section: BlockBisection): Flower = copy(section = section)
}
