package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, OreMaterial }

case class Ore(
  location: BlockLocation,
  material: OreMaterial
) extends MaterialBlock[OreMaterial] {
  override val kind = BlockType.ORE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Ore = copy(location = loc)
  override def withMaterial(mat: OreMaterial): Ore = copy(material = mat)
}
