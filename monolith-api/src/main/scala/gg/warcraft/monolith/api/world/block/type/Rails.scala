package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, PowerableBlock, ShapedBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.shape.RailsShape
import gg.warcraft.monolith.api.world.block.variant.RailsVariant

final case class Rails(
  location: BlockLocation,
  variant: RailsVariant,
  shape: RailsShape,
  powered: Boolean
) extends VariedBlock[RailsVariant] with ShapedBlock[RailsShape] with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.RAILS

  override def withLocation(loc: BlockLocation): Rails = copy(location = loc)
  override def withVariant(variant: RailsVariant): Rails = copy(variant = variant)
  override def withShape(shape: RailsShape): Rails = copy(shape = shape)
  override def withPowered(powered: Boolean): Rails = copy(powered = powered)
}
