package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

case class Dropper(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean
) extends DirectedBlock with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.DROPPER

  override def withLocation(loc: BlockLocation): Dropper = copy(location = loc)
  override def withDirection(dir: BlockFace): Dropper = copy(direction = dir)
  override def withPowered(powered: Boolean): Dropper = copy(powered = powered)
}
