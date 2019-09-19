package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, PowerableBlock }

case class Lectern(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean,
  book: Boolean
) extends DirectionalBlock with PowerableBlock {
  override val kind = BlockType.LECTERN

  /* Java interop */

  def withBook(book: Boolean): Lectern = copy(book = book)

  override def withLocation(loc: BlockLocation): Lectern = copy(location = loc)
  override def withFacing(facing: BlockFace): Lectern = copy(facing = facing)
  override def withPowered(powered: Boolean): Lectern = copy(powered = powered)
}
