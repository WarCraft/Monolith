package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

case class Observer(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean
) extends DirectedBlock with PowerableBlock {
  override val kind = BlockType.OBSERVER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Observer = copy(location = loc)
  override def withDirection(dir: BlockFace): Observer = copy(direction = dir)
  override def withPowered(powered: Boolean): Observer = copy(powered = powered)
}
