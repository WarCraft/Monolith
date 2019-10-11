package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

case class Torch(
  location: BlockLocation,
  direction: BlockFace,
  wall: Boolean
) extends DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.TORCH

  def withWall(wall: Boolean): Torch = copy(wall = wall) // TODO this is superseded by direction?

  override def withLocation(loc: BlockLocation): Torch = copy(location = loc)
  override def withDirection(dir: BlockFace): Torch = copy(direction = dir)
}
