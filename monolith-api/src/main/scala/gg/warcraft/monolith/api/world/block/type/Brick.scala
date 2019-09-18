package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.{ BlockType, MaterialBlock }
import gg.warcraft.monolith.api.world.block.material.BrickMaterial

case class Brick(
  location: BlockLocation,
  material: BrickMaterial
) extends MaterialBlock[BrickMaterial] {
  override val kind = BlockType.BRICK

  /* Java interop */

  override def withLocation(loc: BlockLocation): Brick = copy(location = loc)
  override def withMaterial(mat: BrickMaterial): Brick = copy(material = mat)
}
