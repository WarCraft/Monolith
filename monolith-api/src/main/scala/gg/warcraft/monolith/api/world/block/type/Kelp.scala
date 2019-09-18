package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.KelpState

case class Kelp(
  location: BlockLocation,
  state: KelpState
) extends StatefulBlock[KelpState] {
  override val kind = BlockType.KELP

  /* Java interop */

  override def withLocation(loc: BlockLocation): Kelp = copy(location = loc)
  override def withState(state: KelpState): Kelp = copy(state = state)
}
