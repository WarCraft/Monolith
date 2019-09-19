package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Lever(
  location: BlockLocation,
  facing: BlockFace,
  attached: BlockAttachment,
  powered: Boolean
) extends DirectionalBlock with AttachedBlock with PowerableBlock {
  override val kind = BlockType.LEVER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Lever = copy(location = loc)
  override def withFacing(facing: BlockFace): Lever = copy(facing = facing)
  override def withAttached(attached: BlockAttachment): Lever = copy(attached = attached)
  override def withPowered(powered: Boolean): Lever = copy(powered = powered)
}
