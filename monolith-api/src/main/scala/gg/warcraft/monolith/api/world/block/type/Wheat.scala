package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.state.WheatState
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class Wheat(
  location: BlockLocation,
  state: WheatState
) extends StatefulBlock[WheatState] {
  override val kind = BlockType.WHEAT

  /* Java interop */

  override def withLocation(loc: BlockLocation): Wheat = copy(location = loc)
  override def withState(state: WheatState): Wheat = copy(state = state)
}
