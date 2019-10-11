package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.variant.WeightedPressurePlateVariant
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.state.WeightedPressurePlateState

final case class WeightedPressurePlate(
  location: BlockLocation,
  variant: WeightedPressurePlateVariant,
  state: WeightedPressurePlateState
) extends VariedBlock[WeightedPressurePlateVariant] with StatefulBlock[WeightedPressurePlateState] {

  /* Java interop */

  override val `type` = BlockType.WEIGHTED_PRESSURE_PLATE

  override def withLocation(loc: BlockLocation): WeightedPressurePlate = copy(location = loc)
  override def withVariant(variant: WeightedPressurePlateVariant): WeightedPressurePlate = copy(variant = variant)
  override def withState(state: WeightedPressurePlateState): WeightedPressurePlate = copy(state = state)
}
