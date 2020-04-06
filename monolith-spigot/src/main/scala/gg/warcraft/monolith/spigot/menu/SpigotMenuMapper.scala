package gg.warcraft.monolith.spigot.menu

import java.util.UUID

import gg.warcraft.monolith.api.menu.Menu
import gg.warcraft.monolith.spigot.player.SpigotPlayer
import org.bukkit.inventory.{Inventory, InventoryHolder}
import org.bukkit.Server

private class SpigotMenuHolder(player: SpigotPlayer) extends InventoryHolder {
  override def getInventory: Inventory = player.getInventory
}

class SpigotMenuMapper(implicit server: Server, buttonMapper: SpigotButtonMapper) {
  def map(menu: Menu, playerId: UUID): Inventory = {
    val player = server getPlayer playerId
    val holder = new SpigotMenuHolder(player)
    val inventory = server createInventory (holder, menu.size.slots, menu.title)
    menu.buttons foreach {
      case (slot, button) =>
        val item = buttonMapper map button
        inventory setItem (slot, item)
    }
    inventory
  }
}
