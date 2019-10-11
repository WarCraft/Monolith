package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ Block, BlockType }

final case class DaylightDetector(location: BlockLocation) extends Block {

  /* Java interop */

  override val `type` = BlockType.DAYLIGHT_DETECTOR

  override def withLocation(loc: BlockLocation): DaylightDetector = copy(location = loc)
}
