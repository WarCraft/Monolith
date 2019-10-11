package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.TurtleEggState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

final case class TurtleEgg(
  location: BlockLocation,
  state: TurtleEggState,
) extends StatefulBlock[TurtleEggState] {

  /* Java interop */

  override val `type` = BlockType.TURTLE_EGG

  override def withLocation(loc: BlockLocation): TurtleEgg = copy(location = loc)
  override def withState(state: TurtleEggState): TurtleEgg = copy(state = state)
}
