package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, LightableBlock }

case class RedstoneLamp(
  location: BlockLocation,
  lit: Boolean
) extends LightableBlock {
  override val kind = BlockType.REDSTONE_LAMP

  /* Java interop */

  override def withLocation(loc: BlockLocation): RedstoneLamp = copy(location = loc)
  override def withLit(lit: Boolean): RedstoneLamp = copy(lit = lit)
}
