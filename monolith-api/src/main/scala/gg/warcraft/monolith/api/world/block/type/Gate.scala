package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material.WoodMaterial

case class Gate(
  location: BlockLocation,
  material: WoodMaterial,
  direction: BlockFace,
  open: Boolean,
  powered: Boolean,
  wall: Boolean
) extends MaterialBlock[WoodMaterial] with DirectedBlock with OpenableBlock with PowerableBlock {
  override val kind = BlockType.GATE

  /* Java interop */

  def withWall(wall: Boolean): Gate = copy(wall = wall)

  override def withLocation(loc: BlockLocation): Gate = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Gate = copy(material = mat)
  override def withDirection(dir: BlockFace): Gate = copy(direction = dir)
  override def withOpen(open: Boolean): Gate = copy(open = open)
  override def withPowered(pow: Boolean): Gate = copy(powered = pow)
}
