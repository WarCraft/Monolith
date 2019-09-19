package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.StructureBlockState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class StructureBlock(
  location: BlockLocation,
  state: StructureBlockState
) extends StatefulBlock[StructureBlockState] {
  override val kind = BlockType.STRUCTURE_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): StructureBlock = copy(location = loc)
  override def withState(state: StructureBlockState): StructureBlock = copy(state = state)
}
