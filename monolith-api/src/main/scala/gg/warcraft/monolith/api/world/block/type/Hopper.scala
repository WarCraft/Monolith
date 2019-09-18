package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, PowerableBlock }

case class Hopper(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override val kind = BlockType.HOPPER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Hopper = copy(location = loc)
  override def withFacing(facing: BlockFace): Hopper = copy(facing = facing)
  override def withPowered(powered: Boolean): Hopper = copy(powered = powered)
}
