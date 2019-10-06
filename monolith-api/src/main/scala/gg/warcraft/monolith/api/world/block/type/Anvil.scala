package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.AnvilState

case class Anvil(
  location: BlockLocation,
  state: AnvilState,
  direction: BlockFace
) extends StatefulBlock[AnvilState] with DirectedBlock {
  override val kind = BlockType.ANVIL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Anvil = copy(location = loc)
  override def withState(state: AnvilState): Anvil = copy(state = state)
  override def withDirection(facing: BlockFace): Anvil = copy(direction = facing)
}

