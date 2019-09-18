package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Bedrock(location: BlockLocation) extends Block {
  override val kind = BlockType.BEDROCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): Bedrock = copy(location = loc)
}
