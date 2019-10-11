package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.variant.ComparatorVariant

final case class Comparator(
  location: BlockLocation,
  variant: ComparatorVariant,
  direction: BlockFace,
  powered: Boolean
) extends VariedBlock[ComparatorVariant] with DirectedBlock with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.COMPARATOR

  override def withLocation(loc: BlockLocation): Comparator = copy(location = loc)
  override def withVariant(variant: ComparatorVariant): Comparator = copy(variant = variant)
  override def withDirection(dir: BlockFace): Comparator = copy(direction = dir)
  override def withPowered(powered: Boolean): Comparator = copy(powered = powered)
}
