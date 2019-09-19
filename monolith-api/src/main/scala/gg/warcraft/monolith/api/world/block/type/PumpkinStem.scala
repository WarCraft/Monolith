package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectableBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.PumpkinStemState

case class PumpkinStem(
  location: BlockLocation,
  state: PumpkinStemState,
  facing: Option[BlockFace]
) extends StatefulBlock[PumpkinStemState] with DirectableBlock {
  override val kind = BlockType.PUMPKIN_STEM

  /* Java interop */

  override def withLocation(loc: BlockLocation): PumpkinStem = copy(location = loc)
  override def withState(state: PumpkinStemState): PumpkinStem = copy(state = state)
  override def withFacing(facing: Option[BlockFace]): PumpkinStem = copy(facing = facing)
}
