package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectionalBlock, LightableBlock }

case class BlastFurnace(
  location: BlockLocation,
  facing: BlockFace,
  lit: Boolean
) extends DirectionalBlock with LightableBlock {
  override val kind = BlockType.BLAST_FURNACE

  /* Java interop */

  override def withLocation(loc: BlockLocation): BlastFurnace = copy(location = loc)
  override def withFacing(facing: BlockFace): BlastFurnace = copy(facing = facing)
  override def withLit(lit: Boolean): BlastFurnace = copy(lit = lit)
}
