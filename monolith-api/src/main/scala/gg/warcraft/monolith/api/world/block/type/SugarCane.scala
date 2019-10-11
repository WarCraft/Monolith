package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.SugarCaneState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock }

case class SugarCane(
  location: BlockLocation,
  state: SugarCaneState
) extends StatefulBlock[SugarCaneState] {

  /* Java interop */

  override val `type` = BlockType.SUGAR_CANE

  override def withLocation(loc: BlockLocation): SugarCane = copy(state = state)
  override def withState(state: SugarCaneState): SugarCane = copy(state = state)
}
