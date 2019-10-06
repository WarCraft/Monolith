package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Button(
  location: BlockLocation,
  material: ButtonMaterial,
  direction: BlockFace,
  attachment: BlockAttachment,
  powered: Boolean
) extends MaterialBlock[ButtonMaterial] with DirectedBlock with AttachedBlock with PowerableBlock {
  override val kind = BlockType.BUTTON

  /* Java interop */

  override def withLocation(loc: BlockLocation): Button = copy(location = loc)
  override def withMaterial(mat: ButtonMaterial): Button = copy(material = mat)
  override def withDirection(facing: BlockFace): Button = copy(direction = facing)
  override def withAttached(attachment: BlockAttachment): Button = copy(attachment = attachment)
  override def withPowered(powered: Boolean): Button = copy(powered = powered)
}
