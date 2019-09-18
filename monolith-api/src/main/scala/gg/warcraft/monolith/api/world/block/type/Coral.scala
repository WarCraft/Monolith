package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.material.CoralMaterial
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, FloodableBlock, MaterialBlock }

case class Coral(
  location: BlockLocation,
  material: CoralMaterial,
  flooded: Boolean
) extends MaterialBlock[CoralMaterial] with FloodableBlock {
  override val kind = BlockType.CORAL
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): Coral = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): Coral = copy(material = mat)
  override def withFlooded(flooded: Boolean): Coral = copy(flooded = flooded)
}
