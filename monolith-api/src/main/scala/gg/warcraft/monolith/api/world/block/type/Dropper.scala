package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, PowerableBlock }

case class Dropper(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override val kind = BlockType.DROPPER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Dropper = copy(location = loc)
  override def withFacing(facing: BlockFace): Dropper = copy(facing = facing)
  override def withPowered(powered: Boolean): Dropper = copy(powered = powered)
}
