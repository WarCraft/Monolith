package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, LightableBlock }

case class Smoker(
  location: BlockLocation,
  facing: BlockFace,
  lit: Boolean
) extends DirectionalBlock with LightableBlock {
  override val kind = BlockType.SMOKER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Smoker = copy(location = loc)
  override def withFacing(facing: BlockFace): Smoker = copy(facing = facing)
  override def withLit(lit: Boolean): Smoker = copy(lit = lit)
}
