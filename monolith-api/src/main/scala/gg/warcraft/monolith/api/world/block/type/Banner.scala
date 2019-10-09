package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Banner(
  location: BlockLocation,
  color: BlockColor,
  rotation: Option[BlockRotation],
  direction: Option[BlockFace]
) extends ColoredBlock with RotatableBlock with DirectableBlock {
  override val kind = BlockType.BANNER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Banner = copy(location = loc)
  override def withColor(color: BlockColor): Banner = copy(color = color)
  override def withRotation(rotation: Option[BlockRotation]): Banner = copy(rotation = rotation)
  override def withDirection(dir: Option[BlockFace]): Banner = copy(direction = dir)
}
