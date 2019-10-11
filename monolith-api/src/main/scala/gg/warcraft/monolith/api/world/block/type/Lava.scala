package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.LavaState

// TODO add falling: Boolean
final case class Lava(
  location: BlockLocation,
  state: LavaState
) extends StatefulBlock[LavaState] {
  override val liquid: Boolean = true

  /* Java interop */

  override val `type` = BlockType.LAVA

  override def withLocation(loc: BlockLocation): Lava = copy(location = loc)
  override def withState(state: LavaState): Lava = copy(state = state)
}
