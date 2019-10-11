package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class GrassPath(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.GRASS_PATH

  override def withLocation(loc: BlockLocation): GrassPath = copy(location = loc)
}
