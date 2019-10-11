package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, FloodableBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.SeaPickleState

case class SeaPickle(
  location: BlockLocation,
  state: SeaPickleState,
  flooded: Boolean
) extends StatefulBlock[SeaPickleState] with FloodableBlock {

  /* Java interop */

  override val `type` = BlockType.SEA_PICKLE

  override def withLocation(loc: BlockLocation): SeaPickle = copy(location = loc)
  override def withState(state: SeaPickleState): SeaPickle = copy(state = state)
  override def withFlooded(flooded: Boolean): SeaPickle = copy(flooded = flooded)
}
