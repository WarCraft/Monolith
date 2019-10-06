package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, LightableBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class Furnace(
  location: BlockLocation,
  direction: BlockFace,
  lit: Boolean
) extends DirectedBlock with LightableBlock {
  override val kind = BlockType.FURNACE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Furnace = copy(location = loc)
  override def withDirection(facing: BlockFace): Furnace = copy(direction = facing)
  override def withLit(lit: Boolean): Furnace = copy(lit = lit)
}
