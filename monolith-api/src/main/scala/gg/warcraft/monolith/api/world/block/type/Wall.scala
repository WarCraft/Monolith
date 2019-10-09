package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Wall(
  location: BlockLocation,
  material: WallMaterial,
  variant: Option[WallVariant],
  extensions: Set[BlockFace]
) extends MaterialBlock[WallMaterial] with VariableBlock[WallVariant] with ExtendableBlock {
  override val kind = BlockType.WALL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Wall = copy(location = loc)
  override def withMaterial(mat: WallMaterial): Wall = copy(material = mat)
  override def withVariant(variant: Option[WallVariant]): Wall = copy(variant = variant)
  override def withExtensions(extensions: Set[BlockFace]): Wall = copy(extensions = extensions)
}
