package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.CakeState

case class Cake(
  location: BlockLocation,
  state: CakeState
) extends StatefulBlock[CakeState] {
  override val kind = BlockType.CAKE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Cake = copy(location = loc)
  override def withState(state: CakeState): Cake = copy(state = state)
}
