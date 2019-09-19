package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockOrientation, BlockType, OrientedBlock }

case class Log(
  location: BlockLocation,
  orientation: BlockOrientation,
  stripped: Boolean
) extends OrientedBlock {
  override val kind = BlockType.LOG

  /* Java interop */

  def withStripped(stripped: Boolean): Log = copy(stripped = stripped)

  override def withLocation(loc: BlockLocation): Log = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): Log = copy(orientation = orientation)
}
