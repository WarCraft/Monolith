package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.IceVariant

final case class Ice(
  location: BlockLocation,
  variant: IceVariant
) extends VariedBlock[IceVariant] {

  /* Java interop */

  override val `type` = BlockType.ICE

  override def withLocation(loc: BlockLocation): Ice = copy(location = loc)
  override def withVariant(variant: IceVariant): Ice = copy(variant = variant)
}
