package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.MushroomBlockMaterial

// TODO multi-orientations
case class MushroomBlock(
  location: BlockLocation,
  material: MushroomBlockMaterial
) extends MaterialBlock[MushroomBlockMaterial] {
  override val kind = BlockType.MUSHROOM_BLOCK

  /* Java interop */

  override def withLocation(loc: BlockLocation): MushroomBlock = copy(location = loc)
  override def withMaterial(material: MushroomBlockMaterial): MushroomBlock = copy(material = material)
}
