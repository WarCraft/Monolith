package gg.warcraft.monolith.api.world.block

trait BlockFactory {
  def create(`type`: BlockType): Block
  def create[T <: BlockVariant](variant: T): VariableBlock[T]
  def create(`type`: String): Block
}
