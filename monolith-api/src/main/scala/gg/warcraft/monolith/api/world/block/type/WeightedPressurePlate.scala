package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.material.WeightedPressurePlateMaterial
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.WeightedPressurePlateState

case class WeightedPressurePlate(
  location: BlockLocation,
  material: WeightedPressurePlateMaterial,
  state: WeightedPressurePlateState
) extends MaterialBlock[WeightedPressurePlateMaterial] with StatefulBlock[WeightedPressurePlateState] {
  override val kind = BlockType.WEIGHTED_PRESSURE_PLATE

  /* Java interop */

  override def withLocation(loc: BlockLocation): WeightedPressurePlate = copy(location = loc)
  override def withMaterial(material: WeightedPressurePlateMaterial): WeightedPressurePlate = copy(material = material)
  override def withState(state: WeightedPressurePlateState): WeightedPressurePlate = copy(state = state)
}
