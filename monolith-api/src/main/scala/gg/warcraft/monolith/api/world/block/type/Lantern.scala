package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Lantern(
  location: BlockLocation,
  hanging: Boolean
) extends Block {
  override val kind = BlockType.LANTERN

  /* Java interop */

  def withHanging(hanging: Boolean): Lantern = copy(hanging = hanging)

  override def withLocation(loc: BlockLocation): Lantern = copy(location = loc)
}
