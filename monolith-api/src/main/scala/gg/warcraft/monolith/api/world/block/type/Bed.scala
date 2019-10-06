package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Bed(
  location: BlockLocation,
  color: BlockColor,
  direction: BlockFace,
  section: BlockBisection,
  occupied: Boolean
) extends ColoredBlock with DirectedBlock with BisectedBlock {
  override val kind = BlockType.BED

  /* Java interop */

  def withOccupied(occupied: Boolean): Bed = copy(occupied = occupied)

  override def withLocation(loc: BlockLocation): Bed = copy(location = loc)
  override def withColor(color: BlockColor): Bed = copy(color = color)
  override def withDirection(facing: BlockFace): Bed = copy(direction = facing)
  override def withSection(section: BlockBisection): Bed = copy(section = section)
}
