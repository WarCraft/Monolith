package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

// TODO add val which denotes which vials are filled
case class BrewingStand(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.BREWING_STAND

  override def withLocation(loc: BlockLocation): BrewingStand = copy(location = loc)
}
