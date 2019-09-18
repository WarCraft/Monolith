package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, FlowerPotMaterial, MaterialBlock }
import gg.warcraft.monolith.api.world.BlockLocation

case class FlowerPot(
  location: BlockLocation,
  material: FlowerPotMaterial
) extends MaterialBlock[FlowerPotMaterial] {
  override val kind = BlockType.FLOWER_POT

  /* Java interop */

  override def withLocation(loc: BlockLocation): FlowerPot = copy(location = loc)
  override def withMaterial(mat: FlowerPotMaterial): FlowerPot = copy(material = mat)
}
