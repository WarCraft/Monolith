package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.QuartzMaterial

case class Quartz(
  location: BlockLocation,
  material: QuartzMaterial
) extends MaterialBlock[QuartzMaterial] {
  override val kind = BlockType.QUARTZ

  /* Java interop */

  override def withLocation(loc: BlockLocation): Quartz = copy(location = loc)
  override def withMaterial(mat: QuartzMaterial): Quartz = copy(material = mat)
}
