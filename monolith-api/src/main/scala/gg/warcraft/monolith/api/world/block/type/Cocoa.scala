package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.CocoaState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, StatefulBlock }

final case class Cocoa(
  location: BlockLocation,
  state: CocoaState,
  direction: BlockFace
) extends StatefulBlock[CocoaState] with DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.COCOA

  override def withLocation(loc: BlockLocation): Cocoa = copy(location = loc)
  override def withState(state: CocoaState): Cocoa = copy(state = state)
  override def withDirection(dir: BlockFace): Cocoa = copy(direction = dir)
}
