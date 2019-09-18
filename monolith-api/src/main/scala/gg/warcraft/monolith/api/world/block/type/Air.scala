package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.AirMaterial
import gg.warcraft.monolith.api.world.BlockLocation

case class Air(
  location: BlockLocation,
  material: AirMaterial
) extends MaterialBlock[AirMaterial] {
  override val kind = BlockType.AIR
  override val solid: Boolean = false

  /* Java interop */

  override def withLocation(loc: BlockLocation): Air = copy(location = loc)
  override def withMaterial(material: AirMaterial): Air = copy(material = material)
}
