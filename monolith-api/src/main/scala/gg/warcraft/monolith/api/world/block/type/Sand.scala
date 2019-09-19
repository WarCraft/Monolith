package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.material.SandMaterial

case class Sand(
  location: BlockLocation,
  material: SandMaterial
) extends MaterialBlock[SandMaterial] {
  override val kind = BlockType.SAND

  /* Java interop */

  override def withLocation(loc: BlockLocation): Sand = copy(location = loc)
  override def withMaterial(mat: SandMaterial): Sand = copy(material = mat)
}
