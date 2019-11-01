package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.item.{
  Item,
  ItemFactory,
  ItemType,
  ItemVariant,
  VariableItem
}
import javax.inject.Inject

class SpigotItemFactory @Inject() (
    itemMapper: SpigotItemMapper,
    typeMapper: SpigotItemTypeMapper,
    variantMapper: SpigotItemVariantMapper
) extends ItemFactory {
  override def create(`type`: ItemType): Item = {
    val material = typeMapper.map(`type`)
    val item = new SpigotItemStack(material)
    itemMapper.map(item).get
  }

  override def create[T <: ItemVariant](variant: T): VariableItem[T] = {
    val material = variantMapper.map(variant)
    val item = new SpigotItemStack(material)
    itemMapper.map(item).get.asInstanceOf[VariableItem[T]]
  }
}
