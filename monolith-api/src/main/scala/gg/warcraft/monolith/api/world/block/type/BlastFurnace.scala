package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, LightableBlock }

case class BlastFurnace(
  location: BlockLocation,
  direction: BlockFace,
  lit: Boolean
) extends DirectedBlock with LightableBlock {
  override val kind = BlockType.BLAST_FURNACE

  /* Java interop */

  override def withLocation(loc: BlockLocation): BlastFurnace = copy(location = loc)
  override def withDirection(dir: BlockFace): BlastFurnace = copy(direction = dir)
  override def withLit(lit: Boolean): BlastFurnace = copy(lit = lit)
}
