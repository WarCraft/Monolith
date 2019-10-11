package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Campfire(
  location: BlockLocation,
  direction: BlockFace,
  flooded: Boolean,
  lit: Boolean,
  signal: Boolean
) extends DirectedBlock with FloodableBlock with LightableBlock {

  /* Java interop */

  override val `type` = BlockType.CAMPFIRE

  def withSignal(signal: Boolean): Campfire = copy(signal = signal)

  override def withLocation(loc: BlockLocation): Campfire = copy(location = loc)
  override def withDirection(dir: BlockFace): Campfire = copy(direction = dir)
  override def withFlooded(flooded: Boolean): Campfire = copy(flooded = flooded)
  override def withLit(lit: Boolean): Campfire = copy(lit = lit)
}
