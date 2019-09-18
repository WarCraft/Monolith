package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

// TODO frost level
// TODO what block is this?
case class Frost(location: BlockLocation) extends Block {
  override val kind = BlockType.FROST
  override def withLocation(loc: BlockLocation): Frost = copy(location = loc)
}
