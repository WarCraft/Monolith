package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.CoralMaterial

case class CoralBlock(
  location: BlockLocation,
  material: CoralMaterial
) extends MaterialBlock[CoralMaterial] {
  override val kind = BlockType.CORAL_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): CoralBlock = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): CoralBlock = copy(material = mat)
}
