package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

case class Dispenser(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean
) extends DirectedBlock with PowerableBlock {
  override val kind = BlockType.DISPENSER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Dispenser = copy(location = loc)
  override def withDirection(facing: BlockFace): Dispenser = copy(direction = facing)
  override def withPowered(powered: Boolean): Dispenser = copy(powered = powered)
}
