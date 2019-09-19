package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Spawner(location: BlockLocation) extends Block {
  override val kind = BlockType.SPAWNER

  /* Java interop */

  override def withLocation(loc: BlockLocation): Spawner = copy(location = loc)
}
