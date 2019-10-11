package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, LightableBlock }

case class RedstoneLamp(
  location: BlockLocation,
  lit: Boolean
) extends LightableBlock {

  /* Java interop */

  override val `type` = BlockType.REDSTONE_LAMP

  override def withLocation(loc: BlockLocation): RedstoneLamp = copy(location = loc)
  override def withLit(lit: Boolean): RedstoneLamp = copy(lit = lit)
}
