package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

// TODO add size of layer
case class Snow(location: BlockLocation) extends Block {
  override val kind = BlockType.SNOW

  /* Java interop */

  override def withLocation(loc: BlockLocation): Snow = copy(location = loc)
}
