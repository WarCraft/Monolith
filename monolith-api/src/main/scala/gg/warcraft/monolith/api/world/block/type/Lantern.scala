package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

case class Lantern(
  location: BlockLocation,
  hanging: Boolean
) extends Block {

  /* Java interop */

  override val `type` = BlockType.LANTERN

  def withHanging(hanging: Boolean): Lantern = copy(hanging = hanging)

  override def withLocation(loc: BlockLocation): Lantern = copy(location = loc)
}
