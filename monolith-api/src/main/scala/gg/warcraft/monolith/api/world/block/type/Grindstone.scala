package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Grindstone(
  location: BlockLocation,
  facing: BlockFace,
  attachment: BlockAttachment
) extends DirectionalBlock with AttachedBlock {
  override val kind = BlockType.GRINDSTONE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Grindstone = copy(location = loc)
  override def withFacing(facing: BlockFace): Grindstone = copy(facing = facing)
  override def withAttached(attachment: BlockAttachment): Grindstone = copy(attachment = attachment)
}
