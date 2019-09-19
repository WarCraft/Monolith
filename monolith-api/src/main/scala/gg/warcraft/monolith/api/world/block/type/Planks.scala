package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.WoodMaterial

case class Planks(
  location: BlockLocation,
  material: WoodMaterial
) extends MaterialBlock[WoodMaterial] {
  override val kind = BlockType.PLANKS

  /* Java interop */

  override def withLocation(loc: BlockLocation): Planks = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Planks = copy(material = mat)
}
