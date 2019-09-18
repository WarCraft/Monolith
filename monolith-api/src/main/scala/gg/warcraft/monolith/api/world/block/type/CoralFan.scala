package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.material.CoralMaterial
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class CoralFan(
  location: BlockLocation,
  material: CoralMaterial,
  facing: Option[BlockFace],
  flooded: Boolean
) extends MaterialBlock[CoralMaterial] with DirectableBlock with FloodableBlock {
  override val kind = BlockType.CORAL_FAN
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): CoralFan = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): CoralFan = copy(material = mat)
  override def withFacing(facing: Option[BlockFace]): CoralFan = copy(facing = facing)
  override def withFlooded(flooded: Boolean): CoralFan = copy(flooded = flooded)
}
