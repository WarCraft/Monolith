package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, PowerableBlock }

// NOTE book is read-only in Spigot
final case class Lectern(
  location: BlockLocation,
  direction: BlockFace,
  powered: Boolean,
  book: Boolean
) extends DirectedBlock with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.LECTERN

  override def withLocation(loc: BlockLocation): Lectern = copy(location = loc)
  override def withDirection(dir: BlockFace): Lectern = copy(direction = dir)
  override def withPowered(powered: Boolean): Lectern = copy(powered = powered)
}
