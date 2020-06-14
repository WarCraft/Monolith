/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.spigot.item

import java.util.UUID

import gg.warcraft.monolith.api.item.{
  Item, ItemService, ItemType, ItemVariant, StackableItem, VariableItem
}
import gg.warcraft.monolith.api.world.{Location, WorldService}
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.Server

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
    "gg.warcraft.monolith.api.item.variant"

  override def parseData(data: String): Data = try {
    if (data.contains(':')) {
      val Array(enum, value) = data.split(':')
      val clazz = Class.forName(s"$itemVariantPackage.$enum")
      val valueOf = clazz.getMethod("valueOf", classOf[String])
      valueOf.invoke(null, value).asInstanceOf[ItemVariant]
    } else {
      ItemType.valueOf(data)
    }
  } catch {
    case _: ClassNotFoundException =>
      worldService.parseData(data).asInstanceOf[Data]
  }

  // TODO this method is sometimes used for its material mapping
  override def create(data: Data): Item = {
    val material = data match {
      case it: ItemType    => typeMapper.map(it)
      case it: ItemVariant => variantMapper.map(it)
    }
    val item = new SpigotItem(material)
    itemMapper.map(item).get
  }

  override def create[T <: ItemVariant](variant: T): VariableItem[T] =
    create(variant).asInstanceOf[VariableItem[T]]

  override def giveTo(playerId: UUID, data: Data, count: Int): Boolean = {
    val player = server.getPlayer(playerId)
    if (player == null) return false

    val spigotInventory = player.getInventory
    val inventory = new SpigotInventory(spigotInventory)
    create(data) match {
      case it: StackableItem =>
        val requiredSpace = (count / it.maxCount.toFloat).ceil.toInt
        if (inventory.hasSpace(requiredSpace)) {
          val spigotItem = itemMapper.map(it)
          // TODO give multiple items for count > maxCount
          spigotItem.setAmount(count.max(it.maxCount))
          spigotInventory.addItem(spigotItem)
          true
        } else false
      case it =>
        if (inventory.hasSpace(count)) {
          val spigotItem = itemMapper.map(it)
          for (_ <- 1 to count) spigotInventory.addItem(spigotItem)
          true
        } else false
    }
  }

  override def giveTo(playerId: UUID, items: Item*): Boolean = {
    val player = server.getPlayer(playerId)
    if (player == null) return false

    val spigotInventory = player.getInventory
    val inventory = new SpigotInventory(spigotInventory)
    // TODO apply more advanced space logic for mergeable items
    if (!inventory.hasSpace(items.length)) return false

    items.map(itemMapper.map).foreach(spigotInventory.addItem(_))
    true
  }

  override def takeFrom(playerId: UUID, data: Data, count: Int): Boolean = {
    val player = server.getPlayer(playerId)
    if (player == null) return false

    val spigotInventory = player.getInventory
    val item = create(data)
    val spigotItem = itemMapper.map(item)
    if (spigotInventory.containsAtLeast(spigotItem, count)) {
      spigotItem.setAmount(count)
      spigotInventory.removeItem(spigotItem)
      true
    } else false
  }

  override def takeFrom(playerId: UUID, items: Item*): Boolean = {
    val player = server.getPlayer(playerId)
    if (player == null) return false

    val spigotInventory = player.getInventory
    val spigotItems = items.map(itemMapper.map)
    // TODO merge counts of identical items before performing this check
    if (spigotItems.forall(it => spigotInventory.containsAtLeast(it, it.getAmount))) {
      spigotInventory.removeItem(spigotItems: _*)
      true
    } else false
  }

  @varargs override def dropItems(location: Location, items: Item*): Array[UUID] = {
    val spigotLocation = locationMapper.map(location)
    val spigotWorld = spigotLocation.getWorld
    items
      .map(item => {
        val spigotItem = itemMapper.map(item)
        val droppedItem = spigotWorld.dropItemNaturally(spigotLocation, spigotItem)
        droppedItem.getUniqueId
      })
      .toArray
  }
}
