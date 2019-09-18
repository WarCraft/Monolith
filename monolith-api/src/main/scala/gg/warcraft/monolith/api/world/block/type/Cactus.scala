package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.CactusState

case class Cactus(
  location: BlockLocation,
  state: CactusState
) extends StatefulBlock[CactusState] {
  override val kind = BlockType.CACTUS

  /* Java interop */

  override def withLocation(loc: BlockLocation): Cactus = copy(location = loc)
  override def withState(state: CactusState): Cactus = copy(state = state)
}
