package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, LightableBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class Furnace(
  location: BlockLocation,
  facing: BlockFace,
  lit: Boolean
) extends DirectionalBlock with LightableBlock {
  override val kind = BlockType.FURNACE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Furnace = copy(location = loc)
  override def withFacing(facing: BlockFace): Furnace = copy(facing = facing)
  override def withLit(lit: Boolean): Furnace = copy(lit = lit)
}
