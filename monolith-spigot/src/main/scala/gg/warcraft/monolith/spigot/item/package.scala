package gg.warcraft.monolith.spigot

import org.bukkit.entity.{Entity, Item}
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.inventory.{Inventory, ItemStack}

package object item {
  type SpigotEntity = Entity

  type SpigotDrop = Item
  type SpigotItem = ItemStack
  type SpigotItemInventory = Inventory
  type SpigotItemPickupEvent = EntityPickupItemEvent
}
