package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BisectedBlock, BlockBisection, BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.FlowerVariant

case class Flower(
  location: BlockLocation,
  variant: FlowerVariant,
  section: BlockBisection,
  tall: Boolean
) extends VariedBlock[FlowerVariant] with BisectedBlock {
  override val solid: Boolean = false

  /* Java interop */

  override val `type` = BlockType.FLOWER

  def withTall(tall: Boolean): Flower = copy(tall = tall)

  override def withLocation(loc: BlockLocation): Flower = copy(location = loc)
  override def withVariant(variant: FlowerVariant): Flower = copy(variant = variant)
  override def withSection(section: BlockBisection): Flower = copy(section = section)
}
