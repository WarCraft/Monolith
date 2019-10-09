package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

case class Hopper(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean
) extends DirectedBlock with PowerableBlock {
  override val kind = BlockType.HOPPER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Hopper = copy(location = loc)
  override def withDirection(dir: BlockFace): Hopper = copy(direction = dir)
  override def withPowered(powered: Boolean): Hopper = copy(powered = powered)
}
