package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.material.SandstoneMaterial
import gg.warcraft.monolith.api.world.block.state.SandstoneState

case class Sandstone(
  location: BlockLocation,
  material: SandstoneMaterial,
  state: SandstoneState
) extends MaterialBlock[SandstoneMaterial] with StatefulBlock[SandstoneState] {
  override val kind = BlockType.SANDSTONE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Sandstone = copy(location = loc)
  override def withMaterial(mat: SandstoneMaterial): Sandstone = copy(material = mat)
  override def withState(state: SandstoneState): Sandstone = copy(state = state)
}
