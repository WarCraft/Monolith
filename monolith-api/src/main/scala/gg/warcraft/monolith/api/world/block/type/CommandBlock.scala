package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.variant.CommandBlockVariant
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, VariedBlock }

case class CommandBlock(
  location: BlockLocation,
  variant: CommandBlockVariant,
  direction: BlockFace,
  conditional: Boolean
) extends VariedBlock[CommandBlockVariant] with DirectedBlock {
  override val kind = BlockType.COMMAND_BLOCK

  /* Java interop */

  def withConditional(conditional: Boolean): CommandBlock = copy(conditional = conditional)

  override def withLocation(loc: BlockLocation): CommandBlock = copy(location = loc)
  override def withVariant(variant: CommandBlockVariant): CommandBlock = copy(variant = variant)
  override def withDirection(facing: BlockFace): CommandBlock = copy(direction = facing)
}
