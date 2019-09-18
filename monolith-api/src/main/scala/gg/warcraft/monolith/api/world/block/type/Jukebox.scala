package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Jukebox(
  location: BlockLocation,
  record: Boolean
) extends Block {
  override val kind = BlockType.JUKEBOX

  /* Java interop */

  def withRecord(record: Boolean): Jukebox = copy(record = record)

  override def withLocation(loc: BlockLocation): Jukebox = copy(location = loc)
}
