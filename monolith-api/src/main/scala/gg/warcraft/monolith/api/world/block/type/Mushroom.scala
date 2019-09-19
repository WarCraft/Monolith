package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.MushroomMaterial

case class Mushroom(
  location: BlockLocation,
  material: MushroomMaterial
) extends MaterialBlock[MushroomMaterial] {
  override val kind = BlockType.MUSHROOM
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): Mushroom = copy(location = loc)
  override def withMaterial(material: MushroomMaterial): Mushroom = copy(material = material)
}
