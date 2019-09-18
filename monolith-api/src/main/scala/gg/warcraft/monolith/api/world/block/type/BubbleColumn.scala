package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class BubbleColumn(
  location: BlockLocation,
  drag: Boolean
) extends Block {
  override val kind = BlockType.BUBBLE_COLUMN

  /* Java interop */

  def withDrag(drag: Boolean): BubbleColumn = copy(drag = drag)

  override def withLocation(loc: BlockLocation): BubbleColumn = copy(location = loc)
}
