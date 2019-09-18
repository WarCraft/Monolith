package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class GrassPath(location: BlockLocation) extends Block {
  override val kind = BlockType.GRASS_PATH

  /* Java interop */

  override def withLocation(loc: BlockLocation): GrassPath = copy(location = loc)
}
