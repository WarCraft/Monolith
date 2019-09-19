package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.RepeaterState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Repeater(
  location: BlockLocation,
  state: RepeaterState,
  facing: BlockFace,
  powered: Boolean,
  locked: Boolean
) extends StatefulBlock[RepeaterState] with DirectionalBlock with PowerableBlock {
  override val kind = BlockType.REPEATER

  /* Java interop */

  def withLocked(locked: Boolean): Repeater = copy(locked = locked)

  override def withLocation(loc: BlockLocation): Repeater = copy(location = loc)
  override def withState(state: RepeaterState): Repeater = copy(state = state)
  override def withFacing(facing: BlockFace): Repeater = copy(facing = facing)
  override def withPowered(powered: Boolean): Repeater = copy(powered = powered)
}
