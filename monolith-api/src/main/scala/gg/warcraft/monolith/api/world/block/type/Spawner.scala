package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Spawner(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.SPAWNER

  override def withLocation(loc: BlockLocation): Spawner = copy(location = loc)
}
