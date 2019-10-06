package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.CocoaState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, StatefulBlock }

case class Cocoa(
  location: BlockLocation,
  state: CocoaState,
  direction: BlockFace
) extends StatefulBlock[CocoaState] with DirectedBlock {
  override val kind = BlockType.COCOA

  /* Java interop */

  override def withLocation(loc: BlockLocation): Cocoa = copy(location = loc)
  override def withState(state: CocoaState): Cocoa = copy(state = state)
  override def withDirection(facing: BlockFace): Cocoa = copy(direction = facing)
}
