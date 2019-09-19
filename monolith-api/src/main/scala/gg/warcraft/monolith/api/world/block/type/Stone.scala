package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.StoneMaterial

case class Stone(
  location: BlockLocation,
  material: StoneMaterial
) extends MaterialBlock[StoneMaterial] {
  override val kind = BlockType.STONE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Stone = copy(location = loc)
  override def withMaterial(mat: StoneMaterial): Stone = copy(material = mat)
}
