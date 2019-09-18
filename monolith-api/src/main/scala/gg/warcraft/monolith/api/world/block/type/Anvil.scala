package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.AnvilState

case class Anvil(
  location: BlockLocation,
  state: AnvilState,
  facing: BlockFace
) extends StatefulBlock[AnvilState] with DirectionalBlock {
  override val kind = BlockType.ANVIL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Anvil = copy(location = loc)
  override def withState(state: AnvilState): Anvil = copy(state = state)
  override def withFacing(facing: BlockFace): Anvil = copy(facing = facing)
}

