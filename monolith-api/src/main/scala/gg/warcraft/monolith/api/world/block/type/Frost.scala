package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.FrostState

final case class Frost(
  location: BlockLocation,
  state: FrostState
) extends StatefulBlock[FrostState] {

  /* Java interop */

  override val `type` = BlockType.FROST

  override def withLocation(loc: BlockLocation): Frost = copy(location = loc)
  override def withState(state: FrostState): Frost = copy(state = state)
}
