package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class Obsidian(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.OBSIDIAN

  override def withLocation(loc: BlockLocation): Obsidian = copy(location = loc)
}
