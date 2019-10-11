package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class BubbleColumn(
  location: BlockLocation,
  drag: Boolean
) extends Block {

  /* Java interop */

  override val `type` = BlockType.BUBBLE_COLUMN

  def withDrag(drag: Boolean): BubbleColumn = copy(drag = drag)

  override def withLocation(loc: BlockLocation): BubbleColumn = copy(location = loc)
}
