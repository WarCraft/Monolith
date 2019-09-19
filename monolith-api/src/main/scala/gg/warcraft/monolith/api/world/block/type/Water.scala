package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.WaterState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

// TODO add falling: Boolean
case class Water(
  location: BlockLocation,
  state: WaterState
) extends StatefulBlock[WaterState] {
  override val kind = BlockType.WATER
  override val liquid: Boolean = true

  /* Java interop */

  override def withLocation(loc: BlockLocation): Water = copy(location = loc)
  override def withState(state: WaterState): Water = copy(state = state)
}
