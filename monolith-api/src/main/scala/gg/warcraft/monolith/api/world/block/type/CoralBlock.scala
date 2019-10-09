package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.CoralVariant

case class CoralBlock(
  location: BlockLocation,
  variant: CoralVariant
) extends VariedBlock[CoralVariant] {
  override val kind = BlockType.CORAL_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): CoralBlock = copy(location = loc)
  override def withVariant(mat: CoralVariant): CoralBlock = copy(variant = variant)
}
