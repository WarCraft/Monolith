package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.item.{Inventory, Item, ItemType, ItemVariant}

class SpigotInventory(
    private val inventory: SpigotItemInventory
)(
    implicit private val itemMapper: SpigotItemMapper,
    implicit private val itemTypeMapper: SpigotItemTypeMapper,
    implicit private val itemVariantMapper: SpigotItemVariantMapper
) extends Inventory {
  override lazy val items: List[Item] =
    inventory.getContents.map(itemMapper.map).filter(_.isDefined).map(_.get).toList

  // TODO add more elaborate checks for merge-able items
  override def hasSpaceFor(items: Item*): Boolean =
    items.length <= inventory.getSize - inventory.getContents.length

  override def contains(`type`: ItemType, count: Int): Boolean = {
    val material = itemTypeMapper.map(`type`)
    inventory.contains(material, count)
  }

  override def contains(`type`: ItemType): Boolean =
    contains(`type`, count = 1)

  override def contains(variant: ItemVariant, count: Int): Boolean = {
    val material = itemVariantMapper.map(variant)
    inventory.contains(material, count)
  }

  override def contains(variant: ItemVariant): Boolean =
    contains(variant, count = 1)

  override def contains(item: Item): Boolean = {
    val spigotItem = itemMapper.map(item)
    inventory.contains(spigotItem, spigotItem.getAmount)
  }
}
