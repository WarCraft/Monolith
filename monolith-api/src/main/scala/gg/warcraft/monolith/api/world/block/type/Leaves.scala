package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.WoodMaterial

case class Leaves(
  location: BlockLocation,
  material: WoodMaterial
) extends MaterialBlock[WoodMaterial] {
  override val kind = BlockType.LEAVES

  /* Java interop */

  override def withLocation(loc: BlockLocation): Leaves = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Leaves = copy(material = mat)
}
