package gg.warcraft.monolith.spigot.world.item

import java.util.UUID

import gg.warcraft.monolith.api.world.{Location, WorldService}
import gg.warcraft.monolith.api.world.item.{
  Inventory, Item, ItemService, ItemType, ItemVariant, VariableItem
}
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.{Material, Server}
import org.bukkit.inventory.ItemStack

import scala.annotation.varargs

class SpigotItemService(
    private implicit val server: Server,
    private implicit val typeMapper: SpigotItemTypeMapper,
    private implicit val variantMapper: SpigotItemVariantMapper,
    private implicit val itemMapper: SpigotItemMapper,
    private implicit val locationMapper: SpigotLocationMapper,
    private implicit val worldService: WorldService
) extends ItemService {
  private final val itemVariantPackage =
    "gg.warcraft.monolith.api.world.item.variant"

  override def parseData(data: String): Any = {
    if (data.contains(':')) {
      try {
        val Array(enum, value) = data.split(':')
        val clazz = Class.forName(s"$itemVariantPackage.$enum")
        val valueOf = clazz.getMethod("valueOf", classOf[String])
        valueOf.invoke(null, value)
      } catch {
        case _: ClassNotFoundException => worldService.parseData(data)
      }
    } else {
      ItemType.valueOf(data)
    }
  }

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

  override def create(data: Any): Item = {
    val parsedData = data match {
      case it: String => parseData(it)
      case _          => data
    }

    parsedData match {
      case it: ItemType    => create(it)
      case it: ItemVariant => create(it)
    }
  }

  private def getInventory(playerId: UUID): Option[Inventory] = {
    server.getPlayer(playerId) match {
      case null => None
      case it =>
        val inventory = new SpigotInventory(it.getInventory)
        Some(inventory)
    }
  }

  private def giveTo2(playerId: UUID, items: ItemStack*): Boolean = {
    val inventory = getInventory(playerId)
    inventory match {
      case None => false
      case Some(inventory) =>
        if (inventory.hasSpace(items.length)) {
          // TODO items.foreach(player.getInventory.addItem(_))
          true
        } else false
    }
  }

  // TODO safeguard against counts > 64
  override def giveTo(playerId: UUID, `type`: ItemType, count: Int): Boolean = {
    val material = typeMapper.map(`type`)
    val item = new ItemStack(material, count)
    giveTo2(playerId, item)
  }

  override def giveTo(playerId: UUID, variant: ItemVariant, count: Int): Boolean = {
    val material = variantMapper.map(variant)
    val item = new ItemStack(material, count)
    giveTo2(playerId, item)
  }

  override def giveTo(playerId: UUID, items: Item*): Boolean =
    giveTo2(playerId, items.map(itemMapper.map): _*)

  private def takeFrom(playerId: UUID, material: Material): Boolean = {
    val player = server.getPlayer(playerId)
    if (player == null) return false

    val inventory = null
    false // TODO
  }

  override def takeFrom(playerId: UUID, `type`: ItemType, count: Int): Boolean = {
    val material = typeMapper.map(`type`)
    false
  }

  override def takeFrom(playerId: UUID, variant: ItemVariant, count: Int): Boolean =
    ???

  override def takeFrom(playerId: UUID, items: Item*): Boolean = ???

  @varargs override def dropItems(location: Location, items: Item*): Unit = {
    val spigotLocation = locationMapper.map(location)
    items.foreach(item => {
      val spigotItem = itemMapper.map(item)
      spigotLocation.getWorld.dropItemNaturally(spigotLocation, spigotItem)
    })
  }
}
