package gg.warcraft.monolith.api.world.block.`type`

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block._

final case class Door(
  location: BlockLocation,
  material: DoorMaterial,
  direction: BlockFace,
  hinge: BlockHinge,
  section: BlockBisection,
  open: Boolean,
  powered: Boolean
) extends MaterialBlock[DoorMaterial] with DirectedBlock with HingedBlock with BisectedBlock
  with OpenableBlock with PowerableBlock {

  /* Java interop */

  override val `type` = BlockType.DOOR

  override def withLocation(loc: BlockLocation): Door = copy(location = loc)
  override def withMaterial(mat: DoorMaterial): Door = copy(material = mat)
  override def withDirection(dir: BlockFace): Door = copy(direction = dir)
  override def withHinge(hinge: BlockHinge): Door = copy(hinge = hinge)
  override def withSection(section: BlockBisection): Door = copy(section = section)
  override def withOpen(open: Boolean): Door = copy(open = open)
  override def withPowered(powered: Boolean): Door = copy(powered = powered)
}
