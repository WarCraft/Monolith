package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.material.BambooLeavesMaterial
import gg.warcraft.monolith.api.world.block.state.BambooState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, StatefulBlock }

case class Bamboo(
  location: BlockLocation,
  material: BambooLeavesMaterial,
  state: BambooState,
  thick: Boolean
) extends MaterialBlock[BambooLeavesMaterial] with StatefulBlock[BambooState] {
  override val kind = BlockType.BAMBOO

  /* Java interop */

  def withThick(thick: Boolean): Bamboo = copy(thick = thick)

  override def withLocation(loc: BlockLocation): Bamboo = copy(location = loc)
  override def withMaterial(material: BambooLeavesMaterial): Bamboo = copy(material = material)
  override def withState(state: BambooState): Bamboo = copy(state = state)
}
