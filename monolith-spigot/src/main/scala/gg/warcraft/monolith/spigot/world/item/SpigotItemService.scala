package gg.warcraft.monolith.spigot.world.item

import java.util.UUID

import gg.warcraft.monolith.api.world.item.{
  Item,
  ItemService,
  ItemType,
  ItemVariant,
  VariableItem
}
import org.bukkit.Server
import org.bukkit.inventory.ItemStack

class SpigotItemService(
    private implicit val server: Server,
    private implicit val typeMapper: SpigotItemTypeMapper,
    private implicit val variantMapper: SpigotItemVariantMapper,
    private implicit val itemMapper: SpigotItemMapper
) extends ItemService {
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

  private def giveTo(playerId: UUID, item: ItemStack): Boolean = {
    val player = server.getPlayer(playerId)
    if (player == null) return false

    val inventory = new SpigotInventory(player.getInventory)
    val item = new ItemStack(material)
  }

  override def giveTo(playerId: UUID, `type`: ItemType, count: Int): Boolean = {
    val player = server.getPlayer(playerId)
    if (player == null) return false

    val material = typeMapper.map(`type`)
  }

  override def giveTo(playerId: UUID, variant: ItemVariant, count: Int): Boolean =
    ???
  override def giveTo(playerId: UUID, item: Item*): Boolean = ???

  override def takeFrom(playerId: UUID, `type`: ItemType, count: Int): Boolean = ???
  override def takeFrom(playerId: UUID, variant: ItemVariant, count: Int): Boolean =
    ???
  override def takeFrom(playerId: UUID, item: Item*): Boolean = ???

  //    override def remove(`type`: ItemType, count: Int): Boolean = {
  //      val material = itemTypeMapper.map(`type`)
  //      inventory.re
  //    }
  //
  //    override def remove(`type`: ItemType): Boolean =
  //      remove(`type`, count = 1)
  //
  //    override def remove(variant: ItemVariant, count: Int): Boolean = ???
  //
  //    override def remove(variant: ItemVariant): Boolean =
  //      remove(variant, count = 1)
  //
  //    override def remove(item: Item): Boolean = ???
}
