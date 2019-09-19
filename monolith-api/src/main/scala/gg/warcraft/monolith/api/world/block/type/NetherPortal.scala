package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockOrientation, BlockType, OrientedBlock }

case class NetherPortal(
  location: BlockLocation,
  orientation: BlockOrientation
) extends OrientedBlock {
  override val kind = BlockType.NETHER_PORTAL

  /* Java interop */

  override def withLocation(loc: BlockLocation): NetherPortal = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): NetherPortal = copy(orientation = orientation)
}
