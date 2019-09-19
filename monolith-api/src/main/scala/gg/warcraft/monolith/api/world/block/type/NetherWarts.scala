package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.NetherWartState

case class NetherWarts(
  location: BlockLocation,
  state: NetherWartState
) extends StatefulBlock[NetherWartState] {
  override val kind = BlockType.NETHER_WARTS
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): NetherWarts = copy(location = loc)
  override def withState(state: NetherWartState): NetherWarts = copy(state = state)
}
