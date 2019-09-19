package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.PotatoState

case class Potatoes(
  location: BlockLocation,
  state: PotatoState
) extends StatefulBlock[PotatoState] {
  override val kind = BlockType.POTATOES
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): Potatoes = copy(location = loc)
  override def withState(state: PotatoState): Potatoes = copy(state = state)
}
