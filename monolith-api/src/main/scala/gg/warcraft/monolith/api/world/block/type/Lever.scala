package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Lever(
  location: BlockLocation,
  direction: BlockFace,
  attachment: BlockAttachment,
  powered: Boolean
) extends DirectedBlock with AttachedBlock with PowerableBlock {
  override val kind = BlockType.LEVER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Lever = copy(location = loc)
  override def withDirection(facing: BlockFace): Lever = copy(direction = facing)
  override def withAttached(attachment: BlockAttachment): Lever = copy(attachment = attachment)
  override def withPowered(powered: Boolean): Lever = copy(powered = powered)
}
