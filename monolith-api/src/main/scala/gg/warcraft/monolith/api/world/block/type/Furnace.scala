package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, LightableBlock }
import gg.warcraft.monolith.api.world.BlockLocation

final case class Furnace(
  location: BlockLocation,
  direction: BlockFace,
  lit: Boolean
) extends DirectedBlock with LightableBlock {

  /* Java interop */

  override val `type` = BlockType.FURNACE

  override def withLocation(loc: BlockLocation): Furnace = copy(location = loc)
  override def withDirection(dir: BlockFace): Furnace = copy(direction = dir)
  override def withLit(lit: Boolean): Furnace = copy(lit = lit)
}
