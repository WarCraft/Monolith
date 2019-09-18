package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, PowerableBlock }

case class Dispenser(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override val kind = BlockType.DISPENSER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Dispenser = copy(location = loc)
  override def withFacing(facing: BlockFace): Dispenser = copy(facing = facing)
  override def withPowered(powered: Boolean): Dispenser = copy(powered = powered)
}
