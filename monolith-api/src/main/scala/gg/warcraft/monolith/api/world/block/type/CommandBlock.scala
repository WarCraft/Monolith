package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.material.CommandBlockMaterial
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, MaterialBlock }

case class CommandBlock(
  location: BlockLocation,
  material: CommandBlockMaterial,
  facing: BlockFace,
  conditional: Boolean
) extends MaterialBlock[CommandBlockMaterial] with DirectionalBlock {
  override val kind = BlockType.COMMAND_BLOCK

  /* Java interop */

  def withConditional(conditional: Boolean): CommandBlock = copy(conditional = conditional)

  override def withLocation(loc: BlockLocation): CommandBlock = copy(location = loc)
  override def withMaterial(material: CommandBlockMaterial): CommandBlock = copy(material = material)
  override def withFacing(facing: BlockFace): CommandBlock = copy(facing = facing)
}
