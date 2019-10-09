package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.variant.CoralVariant

case class CoralFan(
  location: BlockLocation,
  variant: CoralVariant,
  direction: Option[BlockFace],
  flooded: Boolean
) extends VariedBlock[CoralVariant] with DirectableBlock with FloodableBlock {
  override val kind = BlockType.CORAL_FAN
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): CoralFan = copy(location = loc)
  override def withVariant(variant: CoralVariant): CoralFan = copy(variant = variant)
  override def withDirection(dir: Option[BlockFace]): CoralFan = copy(direction = dir)
  override def withFlooded(flooded: Boolean): CoralFan = copy(flooded = flooded)
}
