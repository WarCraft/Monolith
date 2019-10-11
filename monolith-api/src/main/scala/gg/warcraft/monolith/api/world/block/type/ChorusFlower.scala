package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.ChorusFlowerState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

final case class ChorusFlower(
  location: BlockLocation,
  state: ChorusFlowerState
) extends StatefulBlock[ChorusFlowerState] {

  /* Java interop */

  override val `type` = BlockType.CHORUS_FLOWER

  override def withLocation(loc: BlockLocation): ChorusFlower = copy(location = loc)
  override def withState(state: ChorusFlowerState): ChorusFlower = copy(state = state)
}
