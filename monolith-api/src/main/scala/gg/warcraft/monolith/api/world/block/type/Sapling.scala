package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, StatefulBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.state.SaplingState
import gg.warcraft.monolith.api.world.block.variant.SaplingVariant

// TODO add growth stage state
final case class Sapling(
  location: BlockLocation,
  variant: SaplingVariant,
  state: SaplingState
) extends VariedBlock[SaplingVariant] with StatefulBlock[SaplingState] {

  /* Java interop */

  override val `type` = BlockType.SAPLING

  override def withLocation(loc: BlockLocation): Sapling = copy(location = loc)
  override def withVariant(mat: SaplingVariant): Sapling = copy(variant = variant)
  override def withState(state: SaplingState): Sapling = copy(state = state)
}
