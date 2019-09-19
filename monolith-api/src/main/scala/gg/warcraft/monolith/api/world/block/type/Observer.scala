package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, PowerableBlock }

case class Observer(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override val kind = BlockType.OBSERVER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Observer = copy(location = loc)
  override def withFacing(facing: BlockFace): Observer = copy(facing = facing)
  override def withPowered(powered: Boolean): Observer = copy(powered = powered)
}
