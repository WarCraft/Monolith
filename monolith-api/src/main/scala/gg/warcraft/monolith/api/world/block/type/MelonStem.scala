package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectableBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.MelonStemState

case class MelonStem(
  location: BlockLocation,
  state: MelonStemState,
  facing: Option[BlockFace]
) extends StatefulBlock[MelonStemState] with DirectableBlock {
  override val kind = BlockType.MELON_STEM

  /* Java interop */

  override def withLocation(loc: BlockLocation): MelonStem = copy(location = loc)
  override def withState(state: MelonStemState): MelonStem = copy(state = state)
  override def withFacing(facing: Option[BlockFace]): MelonStem = copy(facing = facing)
}
