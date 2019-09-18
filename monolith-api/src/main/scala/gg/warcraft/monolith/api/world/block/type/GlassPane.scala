package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class GlassPane(
  location: BlockLocation,
  color: Option[BlockColor],
  extensions: Set[BlockFace],
  flooded: Boolean
) extends ColorableBlock with ExtendableBlock with FloodableBlock {
  override val kind = BlockType.GLASS_PANE

  /* Java interop */

  override def withLocation(loc: BlockLocation): GlassPane = copy(location = loc)
  override def withColor(color: Option[BlockColor]): GlassPane = copy(color = color)
  override def withExtensions(extensions: Set[BlockFace]): GlassPane = copy(extensions = extensions)
  override def withFlooded(flooded: Boolean): GlassPane = copy(flooded = flooded)
}
