package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Obsidian(location: BlockLocation) extends Block {
  override val kind = BlockType.OBSIDIAN

  /* Java interop */

  override def withLocation(loc: BlockLocation): Obsidian = copy(location = loc)
}
