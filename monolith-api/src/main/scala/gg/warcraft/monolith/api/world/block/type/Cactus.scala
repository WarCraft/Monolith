package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.CactusState

final case class Cactus(
  location: BlockLocation,
  state: CactusState
) extends StatefulBlock[CactusState] {

  /* Java interop */

  override val `type` = BlockType.CACTUS

  override def withLocation(loc: BlockLocation): Cactus = copy(location = loc)
  override def withState(state: CactusState): Cactus = copy(state = state)
}
