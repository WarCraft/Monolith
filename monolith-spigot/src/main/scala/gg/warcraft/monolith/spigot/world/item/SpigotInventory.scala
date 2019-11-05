package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.item.{Inventory, Item, ItemType, ItemVariant}

class SpigotInventory(
    private val inventory: SpigotItemInventory
)(
    implicit private val itemTypeMapper: SpigotItemTypeMapper,
    implicit private val itemVariantMapper: SpigotItemVariantMapper,
    implicit private val itemMapper: SpigotItemMapper
) extends Inventory {
  override lazy val items: List[Item] =
    inventory.getContents.map(itemMapper.map).filter(_.isDefined).map(_.get).toList

  override def hasSpace(count: Int): Boolean =
    count <= inventory.getSize - inventory.getContents.length

  // TODO add more elaborate checks for merge-able items
  override def hasSpaceFor(items: Item*): Boolean =
    hasSpace(items.length)

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
