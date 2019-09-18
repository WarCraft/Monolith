package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Bed(
  location: BlockLocation,
  color: BlockColor,
  facing: BlockFace,
  section: BlockBisection,
  occupied: Boolean
) extends ColoredBlock with DirectionalBlock with BisectedBlock {
  override val kind = BlockType.BED

  /* Java interop */

  def withOccupied(occupied: Boolean): Bed = copy(occupied = occupied)

  override def withLocation(loc: BlockLocation): Bed = copy(location = loc)
  override def withColor(color: BlockColor): Bed = copy(color = color)
  override def withFacing(facing: BlockFace): Bed = copy(facing = facing)
  override def withSection(section: BlockBisection): Bed = copy(section = section)
}
