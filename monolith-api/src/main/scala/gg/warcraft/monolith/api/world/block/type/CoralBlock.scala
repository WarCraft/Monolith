package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.CoralVariant

final case class CoralBlock(
  location: BlockLocation,
  variant: CoralVariant
) extends VariedBlock[CoralVariant] {

  /* Java interop */

  override val `type` = BlockType.CORAL_BLOCK

  override def withLocation(loc: BlockLocation): CoralBlock = copy(location = loc)
  override def withVariant(mat: CoralVariant): CoralBlock = copy(variant = variant)
}
