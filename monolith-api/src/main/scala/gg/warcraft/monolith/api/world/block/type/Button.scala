package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

final case class Button(
  location: BlockLocation,
  material: ButtonMaterial,
  direction: BlockFace,
  attachment: BlockAttachment,
  powered: Boolean
) extends MaterialBlock[ButtonMaterial] with DirectedBlock with AttachedBlock with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.BUTTON

  override def withLocation(loc: BlockLocation): Button = copy(location = loc)
  override def withMaterial(mat: ButtonMaterial): Button = copy(material = mat)
  override def withDirection(dir: BlockFace): Button = copy(direction = dir)
  override def withAttached(attachment: BlockAttachment): Button = copy(attachment = attachment)
  override def withPowered(powered: Boolean): Button = copy(powered = powered)
}
