package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectableBlock, LightableBlock }

case class Pumpkin(
  location: BlockLocation,
  direction: Option[BlockFace],
  lit: Boolean,
  carved: Boolean
) extends DirectableBlock with LightableBlock {
  override val kind = BlockType.PUMPKIN

  /* Java interop */

  def withCarved(carved: Boolean): Pumpkin = copy(carved = carved)

  override def withLocation(loc: BlockLocation): Pumpkin = copy(location = loc)
  override def withDirection(dir: Option[BlockFace]): Pumpkin = copy(direction = dir)
  override def withLit(lit: Boolean): Pumpkin = copy(lit = lit)
}
