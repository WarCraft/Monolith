package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.IceVariant

case class Ice(
  location: BlockLocation,
  variant: IceVariant
) extends VariedBlock[IceVariant] {
  override val kind = BlockType.ICE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Ice = copy(location = loc)
  override def withVariant(variant: IceVariant): Ice = copy(variant = variant)
}
