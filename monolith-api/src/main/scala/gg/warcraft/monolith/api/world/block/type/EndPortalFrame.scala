package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock }

case class EndPortalFrame(
  location: BlockLocation,
  facing: BlockFace,
  eye: Boolean
) extends DirectionalBlock {
  override val kind = BlockType.END_PORTAL_FRAME

  /* Java interop */

  def withEye(eye: Boolean): EndPortalFrame = copy(eye = eye)

  override def withLocation(loc: BlockLocation): EndPortalFrame = copy(location = loc)
  override def withFacing(facing: BlockFace): EndPortalFrame = copy(facing = facing)
}
