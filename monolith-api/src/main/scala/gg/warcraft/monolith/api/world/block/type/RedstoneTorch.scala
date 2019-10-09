package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectableBlock, LightableBlock }

case class RedstoneTorch(
  location: BlockLocation,
  direction: Option[BlockFace],
  lit: Boolean
) extends DirectableBlock with LightableBlock {
  override val kind = BlockType.REDSTONE_TORCH

  /* Java interop */

  override def withLocation(loc: BlockLocation): RedstoneTorch = copy(location = loc)
  override def withDirection(dir: Option[BlockFace]): RedstoneTorch = copy(direction = dir)
  override def withLit(lit: Boolean): RedstoneTorch = copy(lit = lit)
}
