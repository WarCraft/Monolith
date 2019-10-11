package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, LightableBlock }

final case class Smoker(
  location: BlockLocation,
  direction: BlockFace,
  lit: Boolean
) extends DirectedBlock with LightableBlock {

  /* Java interop */

  override val `type` = BlockType.SMOKER

  override def withLocation(loc: BlockLocation): Smoker = copy(location = loc)
  override def withDirection(dir: BlockFace): Smoker = copy(direction = dir)
  override def withLit(lit: Boolean): Smoker = copy(lit = lit)
}
