package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

case class EndPortalFrame(
  location: BlockLocation,
  direction: BlockFace,
  eye: Boolean
) extends DirectedBlock {
  override val kind = BlockType.END_PORTAL_FRAME

  /* Java interop */

  def withEye(eye: Boolean): EndPortalFrame = copy(eye = eye)

  override def withLocation(loc: BlockLocation): EndPortalFrame = copy(location = loc)
  override def withDirection(dir: BlockFace): EndPortalFrame = copy(direction = dir)
}
