package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.ChestVariant

case class Chest(
  location: BlockLocation,
  variant: ChestVariant,
  direction: BlockFace
) extends VariedBlock[ChestVariant] with DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.CHEST

  override def withLocation(loc: BlockLocation): Chest = copy(location = loc)
  override def withVariant(variant: ChestVariant): Chest = copy(variant = variant)
  override def withDirection(dir: BlockFace): Chest = copy(direction = dir)
}
