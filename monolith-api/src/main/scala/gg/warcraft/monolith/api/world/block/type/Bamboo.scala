package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.state.BambooState
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.variant.BambooVariant

final case class Bamboo(
  location: BlockLocation,
  variant: BambooVariant,
  state: BambooState,
  thick: Boolean
) extends VariedBlock[BambooVariant] with StatefulBlock[BambooState] {

  /* Java interop */

  override val `type` = BlockType.BAMBOO

  def withThick(thick: Boolean): Bamboo = copy(thick = thick)

  override def withLocation(loc: BlockLocation): Bamboo = copy(location = loc)
  override def withVariant(variant: BambooVariant): Bamboo = copy(variant = variant)
  override def withState(state: BambooState): Bamboo = copy(state = state)
}
