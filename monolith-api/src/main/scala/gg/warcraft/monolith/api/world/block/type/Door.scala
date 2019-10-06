package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

case class Door(
  location: BlockLocation,
  material: DoorMaterial,
  direction: BlockFace,
  hinge: BlockHinge,
  section: BlockBisection,
  open: Boolean,
  powered: Boolean
) extends MaterialBlock[DoorMaterial] with DirectedBlock with HingedBlock with BisectedBlock
  with OpenableBlock with PowerableBlock {
  override val kind = BlockType.DOOR

  /* Java interop */

  override def withLocation(loc: BlockLocation): Door = copy(location = loc)
  override def withMaterial(mat: DoorMaterial): Door = copy(material = mat)
  override def withDirection(facing: BlockFace): Door = copy(direction = facing)
  override def withHinge(hinge: BlockHinge): Door = copy(hinge = hinge)
  override def withSection(section: BlockBisection): Door = copy(section = section)
  override def withOpen(open: Boolean): Door = copy(open = open)
  override def withPowered(powered: Boolean): Door = copy(powered = powered)
}
