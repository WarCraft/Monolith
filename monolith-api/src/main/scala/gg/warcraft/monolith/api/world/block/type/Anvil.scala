package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.AnvilVariant

case class Anvil(
  location: BlockLocation,
  variant: AnvilVariant,
  direction: BlockFace
) extends VariedBlock[AnvilVariant] with DirectedBlock {
  override val kind = BlockType.ANVIL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Anvil = copy(location = loc)
  override def withVariant(variant: AnvilVariant): Anvil = copy(variant = variant)
  override def withDirection(facing: BlockFace): Anvil = copy(direction = facing)
}
