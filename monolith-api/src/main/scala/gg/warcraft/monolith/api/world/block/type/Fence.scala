package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Fence(
  location: BlockLocation,
  material: FenceMaterial,
  extensions: Set[BlockFace],
  flooded: Boolean
) extends MaterialBlock[FenceMaterial] with ExtendableBlock with FloodableBlock {
  override val kind = BlockType.FENCE

  /* Java interop */

  override def withLocation(loc: BlockLocation): Fence = copy(location = loc)
  override def withMaterial(mat: FenceMaterial): Fence = copy(material = mat)
  override def withExtensions(extensions: Set[BlockFace]): Fence = copy(extensions = extensions)
  override def withFlooded(flooded: Boolean): Fence = copy(flooded = flooded)
}
