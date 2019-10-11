package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.variant.AirVariant

case class Air(
  location: BlockLocation,
  variant: AirVariant
) extends VariedBlock[AirVariant] {
  override val solid: Boolean = false

  /* Java interop */

  override val `type` = BlockType.AIR

  override def withLocation(loc: BlockLocation): Air = copy(location = loc)
  override def withVariant(variant: AirVariant): Air = copy(variant = variant)
}
