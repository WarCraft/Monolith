package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.CarrotState

case class Carrots(
  location: BlockLocation,
  state: CarrotState
) extends StatefulBlock[CarrotState] {
  override val kind = BlockType.CARROTS

  /* Java interop */

  override def withLocation(loc: BlockLocation): Carrots = copy(location = loc)
  override def withState(state: CarrotState): Carrots = copy(state = state)
}
