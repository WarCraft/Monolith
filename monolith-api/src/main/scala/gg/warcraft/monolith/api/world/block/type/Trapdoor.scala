package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Trapdoor(
  location: BlockLocation,
  material: TrapdoorMaterial,
  facing: BlockFace,
  section: BlockBisection,
  powered: Boolean,
  flooded: Boolean,
  open: Boolean
) extends MaterialBlock[TrapdoorMaterial] with DirectionalBlock with BisectedBlock
  with PowerableBlock with FloodableBlock with OpenableBlock {
  override val kind = BlockType.TRAPDOOR

  /* Java interop */

  override def withLocation(loc: BlockLocation): Trapdoor = copy(location = loc)
  override def withMaterial(mat: TrapdoorMaterial): Trapdoor = copy(material = mat)
  override def withFacing(facing: BlockFace): Trapdoor = copy(facing = facing)
  override def withSection(section: BlockBisection): Trapdoor = copy(section = section)
  override def withPowered(powered: Boolean): Trapdoor = copy(powered = powered)
  override def withFlooded(flooded: Boolean): Trapdoor = copy(flooded = flooded)
  override def withOpen(open: Boolean): Trapdoor = copy(open = open)
}
