package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.EndStoneMaterial

final case class EndStone(
  location: BlockLocation,
  material: EndStoneMaterial
) extends MaterialBlock[EndStoneMaterial] {

  /* Java interop */

  override val `type` = BlockType.END_STONE

  override def withLocation(loc: BlockLocation): EndStone = copy(location = loc)
  override def withMaterial(mat: EndStoneMaterial): EndStone = copy(material = mat)
}
