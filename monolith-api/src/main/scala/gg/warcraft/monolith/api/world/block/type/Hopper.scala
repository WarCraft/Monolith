package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

final case class Hopper(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean
) extends DirectedBlock with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.HOPPER

  override def withLocation(loc: BlockLocation): Hopper = copy(location = loc)
  override def withDirection(dir: BlockFace): Hopper = copy(direction = dir)
  override def withPowered(powered: Boolean): Hopper = copy(powered = powered)
}
