package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, VariableBlock }
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.variant.FlowerPotVariant

case class FlowerPot(
  location: BlockLocation,
  variant: Option[FlowerPotVariant],
) extends VariableBlock[FlowerPotVariant] {
  override val kind = BlockType.FLOWER_POT

  /* Java interop */

  override def withLocation(loc: BlockLocation): FlowerPot = copy(location = loc)
  override def withVariant(variant: Option[FlowerPotVariant]): FlowerPot = copy(variant = variant)
}
