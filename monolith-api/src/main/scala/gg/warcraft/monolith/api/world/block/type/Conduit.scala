package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, FloodableBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class Conduit(
  location: BlockLocation,
  flooded: Boolean
) extends FloodableBlock {
  override val kind = BlockType.CONDUIT

  /* Java interop */

  override def withLocation(loc: BlockLocation): Conduit = copy(location = loc)
  override def withFlooded(flooded: Boolean): Conduit = copy(flooded = flooded)
}
