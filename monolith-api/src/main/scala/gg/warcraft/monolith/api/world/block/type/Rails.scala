package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.material.RailsMaterial
import gg.warcraft.monolith.api.world.block.state.RailsState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, PowerableBlock, StatefulBlock }

case class Rails(
  location: BlockLocation,
  material: RailsMaterial,
  state: RailsState,
  powered: Boolean
) extends MaterialBlock[RailsMaterial] with StatefulBlock[RailsState] with PowerableBlock {
  override val kind = BlockType.RAILS

  /* Java interop */

  override def withLocation(loc: BlockLocation): Rails = copy(location = loc)
  override def withMaterial(mat: RailsMaterial): Rails = copy(material = mat)
  override def withState(state: RailsState): Rails = copy(state = state)
  override def withPowered(powered: Boolean): Rails = copy(powered = powered)
}
