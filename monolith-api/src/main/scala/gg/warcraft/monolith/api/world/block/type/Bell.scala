package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock }

// TODO add attachment val (NOTE this one is different for Bells from attachedTo)
case class Bell(
  location: BlockLocation,
  direction: BlockFace,
) extends DirectedBlock {
  override val kind = BlockType.BELL

  /* Java interop */

  override def withLocation(loc: BlockLocation): Bell = copy(location = loc)
  override def withDirection(dir: BlockFace): Bell = copy(direction = dir)
}
