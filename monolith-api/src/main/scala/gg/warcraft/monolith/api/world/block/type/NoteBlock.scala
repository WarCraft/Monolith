package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, PowerableBlock, StatefulBlock, VariedBlock }
import gg.warcraft.monolith.api.world.block.state.NoteBlockState
import gg.warcraft.monolith.api.world.block.variant.NoteBlockVariant

case class NoteBlock(
  location: BlockLocation,
  variant: NoteBlockVariant,
  state: NoteBlockState,
  powered: Boolean
) extends VariedBlock[NoteBlockVariant] with StatefulBlock[NoteBlockState] with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.NOTE_BLOCK

  override def withLocation(loc: BlockLocation): NoteBlock = copy(location = loc)
  override def withVariant(variant: NoteBlockVariant): NoteBlock = copy(variant = variant)
  override def withState(state: NoteBlockState): NoteBlock = copy(state = state)
  override def withPowered(powered: Boolean): NoteBlock = copy(powered = powered)
}
