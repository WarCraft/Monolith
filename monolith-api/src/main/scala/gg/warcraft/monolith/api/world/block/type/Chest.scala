package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockFace, BlockType, DirectedBlock, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.ChestMaterial

case class Chest(
  location: BlockLocation,
  material: ChestMaterial,
  direction: BlockFace
) extends MaterialBlock[ChestMaterial] with DirectedBlock {
  override val kind = BlockType.CHEST

  /* Java interop */

  override def withLocation(loc: BlockLocation): Chest = copy(location = loc)
  override def withMaterial(material: ChestMaterial): Chest = copy(material = material)
  override def withDirection(facing: BlockFace): Chest = copy(direction = facing)
}
