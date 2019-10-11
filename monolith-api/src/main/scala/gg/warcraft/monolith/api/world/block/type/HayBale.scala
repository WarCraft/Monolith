package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockOrientation, BlockType, OrientedBlock }

case class HayBale(
  location: BlockLocation,
  orientation: BlockOrientation
) extends OrientedBlock {

  /* Java interop */

  override val `type` = BlockType.HAY_BALE

  override def withLocation(loc: BlockLocation): HayBale = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): HayBale = copy(orientation = orientation)
}
