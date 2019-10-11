package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.CobblestoneVariant

case class Cobblestone(
  location: BlockLocation,
  variant: CobblestoneVariant
) extends VariedBlock[CobblestoneVariant] {

  /* Java interop */

  override val `type` = BlockType.COBBLESTONE

  override def withLocation(loc: BlockLocation): Cobblestone = copy(location = loc)
  override def withVariant(variant: CobblestoneVariant): Cobblestone = copy(variant =variant)
}
