package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectableBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.PumpkinStemState

final case class PumpkinStem(
  location: BlockLocation,
  state: PumpkinStemState,
  direction: Option[BlockFace]
) extends StatefulBlock[PumpkinStemState] with DirectableBlock {

  /* Java interop */

  override val `type` = BlockType.PUMPKIN_STEM

  override def withLocation(loc: BlockLocation): PumpkinStem = copy(location = loc)
  override def withState(state: PumpkinStemState): PumpkinStem = copy(state = state)
  override def withDirection(dir: Option[BlockFace]): PumpkinStem = copy(direction = dir)
}
