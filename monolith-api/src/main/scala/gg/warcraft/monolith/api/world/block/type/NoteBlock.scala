package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock, PowerableBlock, StatefulBlock }
import gg.warcraft.monolith.api.world.block.material.NoteBlockMaterial
import gg.warcraft.monolith.api.world.block.state.NoteBlockState

case class NoteBlock(
  location: BlockLocation,
  material: NoteBlockMaterial,
  state: NoteBlockState,
  powered: Boolean
) extends MaterialBlock[NoteBlockMaterial] with StatefulBlock[NoteBlockState] with PowerableBlock {
  override val kind = BlockType.NOTE_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): NoteBlock = copy(location = loc)
  override def withMaterial(mat: NoteBlockMaterial): NoteBlock = copy(material = mat)
  override def withState(state: NoteBlockState): NoteBlock = copy(state = state)
  override def withPowered(powered: Boolean): NoteBlock = copy(powered = powered)
}
