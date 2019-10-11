package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.material.SandMaterial

final case class Sand(
  location: BlockLocation,
  material: SandMaterial
) extends MaterialBlock[SandMaterial] {

  /* Java interop */

  override val `type` = BlockType.SAND

  override def withLocation(loc: BlockLocation): Sand = copy(location = loc)
  override def withMaterial(mat: SandMaterial): Sand = copy(material = mat)
}
