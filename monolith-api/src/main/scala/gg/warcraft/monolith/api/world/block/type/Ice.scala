package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.material.IceMaterial
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }

case class Ice(
  location: BlockLocation,
  material: IceMaterial
) extends MaterialBlock[IceMaterial] {
  override val kind = BlockType.ICE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Ice = copy(location = loc)
  override def withMaterial(mat: IceMaterial): Ice = copy(material = mat)
}
