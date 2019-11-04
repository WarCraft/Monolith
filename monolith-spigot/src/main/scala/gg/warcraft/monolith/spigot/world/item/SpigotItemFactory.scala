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
  private final val itemVariantPackage =
    "gg.warcraft.monolith.api.world.item.variant"

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

  override def create(`type`: String): Item = {
    if (`type`.contains(':')) {
      val Array(enum, value) = `type`.split(':')
      val clazz = Class.forName(s"$itemVariantPackage.$enum")
      val valueOf = clazz.getMethod("valueOf", classOf[String])
      val variant = valueOf.invoke(null, value).asInstanceOf[ItemVariant]
      create(variant).asInstanceOf[Item]
    } else {
      create(ItemType.valueOf(`type`))
    }
  }
}
