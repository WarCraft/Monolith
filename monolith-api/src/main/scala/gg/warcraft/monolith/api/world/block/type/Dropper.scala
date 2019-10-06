package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

case class Dropper(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean
) extends DirectedBlock with PowerableBlock {
  override val kind = BlockType.DROPPER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Dropper = copy(location = loc)
  override def withDirection(facing: BlockFace): Dropper = copy(direction = facing)
  override def withPowered(powered: Boolean): Dropper = copy(powered = powered)
}
