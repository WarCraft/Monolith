package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.variant.CoralVariant
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, FloodableBlock, VariedBlock }

case class Coral(
  location: BlockLocation,
  variant: CoralVariant,
  flooded: Boolean
) extends VariedBlock[CoralVariant] with FloodableBlock {
  override val solid: Boolean = false

  /* Java interop */

  override val `type` = BlockType.CORAL

  override def withLocation(loc: BlockLocation): Coral = copy(location = loc)
  override def withVariant(variant: CoralVariant): Coral = copy(variant = variant)
  override def withFlooded(flooded: Boolean): Coral = copy(flooded = flooded)
}
