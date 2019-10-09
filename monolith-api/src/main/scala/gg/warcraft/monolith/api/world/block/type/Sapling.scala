package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, SaplingMaterial, StatefulBlock }
import gg.warcraft.monolith.api.world.block.state.SaplingState

// TODO add growth stage state
case class Sapling(
  location: BlockLocation,
  material: SaplingMaterial,
  state: SaplingState
) extends MaterialBlock[SaplingMaterial] with StatefulBlock[SaplingState] {
  override val kind = BlockType.SAPLING

  /* Java interop */

  override def withLocation(loc: BlockLocation): Sapling = copy(location = loc)
  override def withMaterial(mat: SaplingMaterial): Sapling = copy(material = mat)
  override def withState(state: SaplingState): Sapling = copy(state = state)
}
