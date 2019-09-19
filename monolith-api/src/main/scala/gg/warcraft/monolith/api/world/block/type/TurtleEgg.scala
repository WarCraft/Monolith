package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.TurtleEggState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class TurtleEgg(
  location: BlockLocation,
  state: TurtleEggState,
  count: Int
) extends StatefulBlock[TurtleEggState] {
  override val kind = BlockType.TURTLE_EGG

  /* Java interop */

  def withCount(count: Int): TurtleEgg = copy(count = count)

  override def withLocation(loc: BlockLocation): TurtleEgg = copy(location = loc)
  override def withState(state: TurtleEggState): TurtleEgg = copy(state = state)
}
