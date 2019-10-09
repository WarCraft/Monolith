package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, LightableBlock }

case class Smoker(
  location: BlockLocation,
  direction: BlockFace,
  lit: Boolean
) extends DirectedBlock with LightableBlock {
  override val kind = BlockType.SMOKER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Smoker = copy(location = loc)
  override def withDirection(dir: BlockFace): Smoker = copy(direction = dir)
  override def withLit(lit: Boolean): Smoker = copy(lit = lit)
}
