package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

case class Torch(
  location: BlockLocation,
  direction: BlockFace,
  wall: Boolean
) extends DirectedBlock {
  override val kind = BlockType.TORCH

  /* Java interop */

  def withWall(wall: Boolean): Torch = copy(wall = wall)

  override def withLocation(loc: BlockLocation): Torch = copy(location = loc)
  override def withDirection(dir: BlockFace): Torch = copy(direction = dir)
}
