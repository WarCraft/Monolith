package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.PrismarineMaterial
import gg.warcraft.monolith.api.world.BlockLocation

case class Prismarine(
  location: BlockLocation,
  material: PrismarineMaterial
) extends MaterialBlock[PrismarineMaterial] {
  override val kind = BlockType.PRISMARINE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Prismarine = copy(location = loc)
  override def withMaterial(mat: PrismarineMaterial): Prismarine = copy(material = mat)
}
