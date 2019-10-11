package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

// TODO add size of layer
final case class Snow(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.SNOW

  override def withLocation(loc: BlockLocation): Snow = copy(location = loc)
}
