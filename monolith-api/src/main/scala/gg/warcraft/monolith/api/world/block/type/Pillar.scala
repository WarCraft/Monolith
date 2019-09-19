package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, PillarMaterial }

case class Pillar(
  location: BlockLocation,
  material: PillarMaterial
) extends MaterialBlock[PillarMaterial] {
  override val kind = BlockType.PILLAR

  /* Java interop */

  override def withLocation(loc: BlockLocation): Pillar = copy(location = loc)
  override def withMaterial(mat: PillarMaterial): Pillar = copy(material = mat)
}
