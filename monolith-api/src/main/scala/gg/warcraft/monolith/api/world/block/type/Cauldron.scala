package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.CauldronState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class Cauldron(
  location: BlockLocation,
  state: CauldronState
) extends StatefulBlock[CauldronState] {
  override val kind = BlockType.CAULDRON

  /* Java interop */

  override def withLocation(loc: BlockLocation): Cauldron = copy(location = loc)
  override def withState(state: CauldronState): Cauldron = copy(state = state)
}
