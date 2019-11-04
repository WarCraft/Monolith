package gg.warcraft.monolith.api.world.item

trait ItemFactory {
  def create(`type`: ItemType): Item
  def create[T <: ItemVariant](variant: T): VariableItem[T]
  def create(variant: String): Item
}
