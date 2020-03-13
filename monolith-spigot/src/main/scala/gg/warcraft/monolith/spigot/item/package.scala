package gg.warcraft.monolith.spigot.world

import org.bukkit.entity.{Entity, Item}
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.inventory.{Inventory, ItemStack}

package object item {
  type SpigotEntity = Entity

  type SpigotItem = Item
  type SpigotItemStack = ItemStack
  type SpigotItemInventory = Inventory
  type SpigotItemPickupEvent = EntityPickupItemEvent
}
