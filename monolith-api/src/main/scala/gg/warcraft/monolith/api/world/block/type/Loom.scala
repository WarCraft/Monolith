package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

final case class Loom(
  location: BlockLocation,
  direction: BlockFace
) extends DirectedBlock {

  /* Java interop */

  override val `type` = BlockType.LOOM

  override def withLocation(loc: BlockLocation): Loom = copy(location = loc)
  override def withDirection(dir: BlockFace): Loom = copy(direction = dir)
}
