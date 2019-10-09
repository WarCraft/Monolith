package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.QuartzVariant

case class Quartz(
  location: BlockLocation,
  variant: QuartzVariant
) extends VariedBlock[QuartzVariant] {
  override val kind = BlockType.QUARTZ

  /* Java interop */

  override def withLocation(loc: BlockLocation): Quartz = copy(location = loc)
  override def withVariant(variant: QuartzVariant): Quartz = copy(variant = variant)
}
