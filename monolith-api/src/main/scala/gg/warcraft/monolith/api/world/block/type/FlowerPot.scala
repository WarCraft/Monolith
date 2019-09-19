package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, FlowerPotMaterial, MaterialBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.state.FlowerPotState

case class FlowerPot(
  location: BlockLocation,
  material: FlowerPotMaterial,
  state: FlowerPotState,
) extends MaterialBlock[FlowerPotMaterial] with StatefulBlock[FlowerPotState] {
  override val kind = BlockType.FLOWER_POT

  /* Java interop */

  override def withLocation(loc: BlockLocation): FlowerPot = copy(location = loc)
  override def withMaterial(mat: FlowerPotMaterial): FlowerPot = copy(material = mat)
  override def withState(state: FlowerPotState): FlowerPot = copy(state = state)
}
