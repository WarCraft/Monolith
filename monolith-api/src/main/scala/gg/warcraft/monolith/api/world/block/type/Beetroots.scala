package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.BeetrootState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class Beetroots(
  location: BlockLocation,
  state: BeetrootState
) extends StatefulBlock[BeetrootState] {
  override val solid: Boolean = false

  /* Java interop */

  override val `type` = BlockType.BEETROOTS

  override def withLocation(loc: BlockLocation): Beetroots = copy(location = loc)
  override def withState(state: BeetrootState): Beetroots = copy(state = state)
}
