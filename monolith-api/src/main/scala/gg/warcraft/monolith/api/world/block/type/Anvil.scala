package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.AnvilVariant

final case class Anvil(
  location: BlockLocation,
  variant: AnvilVariant,
  direction: BlockFace
) extends VariedBlock[AnvilVariant] with DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.ANVIL

  override def withLocation(loc: BlockLocation): Anvil = copy(location = loc)
  override def withVariant(variant: AnvilVariant): Anvil = copy(variant = variant)
  override def withDirection(dir: BlockFace): Anvil = copy(direction = dir)
}
