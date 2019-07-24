package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.world.BlockLocation

trait Block {
  val location: BlockLocation
  val isLiquid: Boolean = false
  val isSolid: Boolean = true
  def withLocation(loc: BlockLocation): Block
}

trait AttachedBlock extends Block {
  val attachedTo: Block
  def withAttachedTo(attachedTo: Block): AttachedBlock
}

trait BisectedBlock extends Block {
  val section: BlockBisection
  def withSection(section: BlockBisection): BisectedBlock
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

trait MaterialBlock[T] extends Block {
  val material: T
  def withMaterial(material: T): MaterialBlock[T]
}

trait OpenableBlock extends Block {
  val open: Boolean
  def withOpen(open: Boolean): OpenableBlock
}

trait PottableBlock extends Block {
  val potted: Boolean
  def withPotted(potted: Boolean): PottableBlock
}

trait PowerableBlock extends Block {
  val powered: Boolean
  def withPowered(powered: Boolean): PowerableBlock
}

trait StatefulBlock[T] extends Block {
  val state: T
  def withState(state: T): StatefulBlock[T]
}

trait StickyBlock extends Block {
  val sticky: Boolean
  def withSticky(sticky: Boolean): StickyBlock
}
