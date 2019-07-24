package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.world.BlockLocation

trait Block {
  val location: BlockLocation
  val isLiquid: Boolean = false
  val isSolid: Boolean = true
  def withLocation(loc: BlockLocation): Block
}

trait AttachableBlock extends Block {
  val attachedTo: Option[Block]
  def withAttachedTo(attachedTo: Option[Block]): AttachableBlock
}

trait AttachedBlock extends Block {
  val attachedTo: Block
  def withAttachedTo(attachedTo: Block): AttachedBlock
}

trait BisectedBlock extends Block {
  val section: BlockBisection
  def withSection(section: BlockBisection): BisectedBlock
}

trait ColorableBlock extends Block {
  val color: Option[BlockColor]
  def withColor(color: Option[BlockColor]): ColorableBlock
}

trait ColoredBlock extends Block {
  val color: BlockColor
  def withColor(color: BlockColor): ColoredBlock
}

trait DirectionalBlock extends Block {
  val facing: BlockFace
  def withFacing(facing: BlockFace): DirectionalBlock
}

trait InvertableBlock extends Block {
  val inverted: Boolean
  def withInverted(inverted: Boolean): InvertableBlock
}

trait LightableBlock extends Block {
  val lit: Boolean
  def withLit(lit: Boolean): LightableBlock
}

trait MaterialBlock[T] extends Block {
  val material: T
  def withMaterial(material: T): MaterialBlock[T]
}

trait OpenableBlock extends Block {
  val open: Boolean
  def withOpen(open: Boolean): OpenableBlock
}

trait OrientableBlock extends Block {
  val orientation: BlockOrientation
  def withOrientation(orientation: BlockOrientation): OrientableBlock
}

trait PottableBlock extends Block {
  val potted: Boolean
  def withPotted(potted: Boolean): PottableBlock
}

trait PowerableBlock extends Block {
  val powered: Boolean
  def withPowered(powered: Boolean): PowerableBlock
}

trait SnowableBlock extends Block {
  val snowy: Boolean
  def withSnowy(snowy: Boolean): SnowableBlock
}

trait StatefulBlock[T] extends Block {
  val state: T
  def withState(state: T): StatefulBlock[T]
}

trait StickyBlock extends Block {
  val sticky: Boolean
  def withSticky(sticky: Boolean): StickyBlock
}
