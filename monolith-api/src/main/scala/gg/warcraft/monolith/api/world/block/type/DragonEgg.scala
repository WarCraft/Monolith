package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class DragonEgg(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.DRAGON_EGG

  override def withLocation(loc: BlockLocation): DragonEgg = copy(location = loc)
}
