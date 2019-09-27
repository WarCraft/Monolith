package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockOrientation, BlockType, MaterialBlock, OrientedBlock }
import gg.warcraft.monolith.api.world.block.material.WoodMaterial

case class Log(
  location: BlockLocation,
  material: WoodMaterial,
  orientation: BlockOrientation,
  stripped: Boolean
) extends MaterialBlock[WoodMaterial] with OrientedBlock {
  override val kind = BlockType.LOG

  /* Java interop */

  def withStripped(stripped: Boolean): Log = copy(stripped = stripped)

  override def withLocation(loc: BlockLocation): Log = copy(location = loc)
  override def withMaterial(material: WoodMaterial): Log = copy(material = material)
  override def withOrientation(orientation: BlockOrientation): Log = copy(orientation = orientation)
}
