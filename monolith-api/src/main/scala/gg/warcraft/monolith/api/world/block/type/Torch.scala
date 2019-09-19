package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock }

case class Torch(
  location: BlockLocation,
  facing: BlockFace,
  wall: Boolean
) extends DirectionalBlock {
  override val kind = BlockType.TORCH

  /* Java interop */

  def withWall(wall: Boolean): Torch = copy(wall = wall)

  override def withLocation(loc: BlockLocation): Torch = copy(location = loc)
  override def withFacing(facing: BlockFace): Torch = copy(facing = facing)
}
