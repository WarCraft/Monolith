package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectableBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.MelonStemState

case class MelonStem(
  location: BlockLocation,
  state: MelonStemState,
  direction: Option[BlockFace]
) extends StatefulBlock[MelonStemState] with DirectableBlock {

  /* Java interop */

  override val `type` = BlockType.MELON_STEM

  override def withLocation(loc: BlockLocation): MelonStem = copy(location = loc)
  override def withState(state: MelonStemState): MelonStem = copy(state = state)
  override def withDirection(dir: Option[BlockFace]): MelonStem = copy(direction = dir)
}
