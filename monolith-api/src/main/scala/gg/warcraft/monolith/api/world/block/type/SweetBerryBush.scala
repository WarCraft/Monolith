package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.SweetBerryState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class SweetBerryBush(
  location: BlockLocation,
  state: SweetBerryState
) extends StatefulBlock[SweetBerryState] {
  override val solid: Boolean = false

  /* Java interop */

  override val `type` = BlockType.SWEET_BERRY_BUSH

  override def withLocation(loc: BlockLocation): SweetBerryBush = copy(location = loc)
  override def withState(state: SweetBerryState): SweetBerryBush = copy(state = state)
}
