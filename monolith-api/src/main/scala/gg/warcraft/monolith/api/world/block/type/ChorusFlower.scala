package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.ChorusFlowerState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class ChorusFlower(
  location: BlockLocation,
  state: ChorusFlowerState
) extends StatefulBlock[ChorusFlowerState] {
  override val kind = BlockType.CHORUS_FLOWER

  /* Java interop */

  override def withLocation(loc: BlockLocation): ChorusFlower = copy(location = loc)
  override def withState(state: ChorusFlowerState): ChorusFlower = copy(state = state)
}
