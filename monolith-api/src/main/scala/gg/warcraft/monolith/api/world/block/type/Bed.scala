package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

// NOTE occupied is read-only in Spigot
case class Bed(
  location: BlockLocation,
  color: BlockColor,
  direction: BlockFace,
  section: BlockBisection,
  occupied: Boolean
) extends ColoredBlock with DirectedBlock with BisectedBlock {
  override val kind = BlockType.BED

  /* Java interop */

  override def withLocation(loc: BlockLocation): Bed = copy(location = loc)
  override def withColor(color: BlockColor): Bed = copy(color = color)
  override def withDirection(dir: BlockFace): Bed = copy(direction = dir)
  override def withSection(section: BlockBisection): Bed = copy(section = section)
}
