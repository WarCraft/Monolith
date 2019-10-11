package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, LightableBlock }

case class BlastFurnace(
  location: BlockLocation,
  direction: BlockFace,
  lit: Boolean
) extends DirectedBlock with LightableBlock {

  /* Java interop */

  override val `type` = BlockType.BLAST_FURNACE

  override def withLocation(loc: BlockLocation): BlastFurnace = copy(location = loc)
  override def withDirection(dir: BlockFace): BlastFurnace = copy(direction = dir)
  override def withLit(lit: Boolean): BlastFurnace = copy(lit = lit)
}
