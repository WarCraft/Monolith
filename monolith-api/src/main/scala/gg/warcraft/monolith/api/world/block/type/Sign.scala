package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Sign(
  lines: List[String],
  location: BlockLocation,
  facing: Option[BlockFace],
  rotation: Option[BlockRotation],
  flooded: Boolean
) extends DirectableBlock with RotatableBlock with FloodableBlock {
  override val kind = BlockType.SIGN

  /* Java interop */

  def withLines(lines: List[String]): Sign = copy(lines = lines.slice(0, 4))

  override def withLocation(loc: BlockLocation): Sign = copy(location = loc)
  override def withFacing(facing: Option[BlockFace]): Sign = copy(facing = facing)
  override def withRotation(rotation: Option[BlockRotation]): Sign = copy(rotation = rotation)
  override def withFlooded(flooded: Boolean): Sign = copy(flooded = flooded)
}
