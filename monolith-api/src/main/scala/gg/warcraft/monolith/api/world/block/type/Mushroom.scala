package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.MushroomVariant

case class Mushroom(
  location: BlockLocation,
  variant: MushroomVariant
) extends VariedBlock[MushroomVariant] {
  override val kind = BlockType.MUSHROOM
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): Mushroom = copy(location = loc)
  override def withVariant(variant: MushroomVariant): Mushroom = copy(variant = variant)
}
