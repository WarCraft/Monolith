package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

final case class Dispenser(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean
) extends DirectedBlock with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.DISPENSER

  override def withLocation(loc: BlockLocation): Dispenser = copy(location = loc)
  override def withDirection(dir: BlockFace): Dispenser = copy(direction = dir)
  override def withPowered(powered: Boolean): Dispenser = copy(powered = powered)
}
