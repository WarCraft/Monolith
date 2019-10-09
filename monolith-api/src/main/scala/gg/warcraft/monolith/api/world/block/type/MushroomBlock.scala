package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.MushroomBlockVariant

// TODO multi-orientations
case class MushroomBlock(
  location: BlockLocation,
  variant: MushroomBlockVariant
) extends VariedBlock[MushroomBlockVariant] {
  override val kind = BlockType.MUSHROOM_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): MushroomBlock = copy(location = loc)
  override def withVariant(variant: MushroomBlockVariant): MushroomBlock = copy(variant = variant)
}
