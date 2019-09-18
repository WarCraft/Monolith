package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.CocoaState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, StatefulBlock }

case class Cocoa(
  location: BlockLocation,
  state: CocoaState,
  facing: BlockFace
) extends StatefulBlock[CocoaState] with DirectionalBlock {
  override val kind = BlockType.COCOA

  /* Java interop */

  override def withLocation(loc: BlockLocation): Cocoa = copy(location = loc)
  override def withState(state: CocoaState): Cocoa = copy(state = state)
  override def withFacing(facing: BlockFace): Cocoa = copy(facing = facing)
}
