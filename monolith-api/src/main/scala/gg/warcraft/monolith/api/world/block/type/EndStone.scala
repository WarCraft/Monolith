package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.EndStoneMaterial

case class EndStone(
  location: BlockLocation,
  material: EndStoneMaterial
) extends MaterialBlock[EndStoneMaterial] {
  override val kind = BlockType.STONE

  /* Java interop */

  override def withLocation(loc: BlockLocation): EndStone = copy(location = loc)
  override def withMaterial(mat: EndStoneMaterial): EndStone = copy(material = mat)
}
