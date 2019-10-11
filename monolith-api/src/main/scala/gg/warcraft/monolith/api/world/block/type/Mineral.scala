package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, MineralMaterial }

final case class Mineral(
  location: BlockLocation,
  material: MineralMaterial
) extends MaterialBlock[MineralMaterial] {

  /* Java interop */

  override val `type` = BlockType.MINERAL

  override def withLocation(loc: BlockLocation): Mineral = copy(location = loc)
  override def withMaterial(mat: MineralMaterial): Mineral = copy(material = mat)
}
