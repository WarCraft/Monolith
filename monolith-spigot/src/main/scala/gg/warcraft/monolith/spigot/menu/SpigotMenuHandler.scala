package gg.warcraft.monolith.spigot.menu

import gg.warcraft.monolith.api.menu.{Click, Menu, MenuService}
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.event.inventory.InventoryClickEvent

class SpigotMenuHandler(implicit menuService: MenuService) extends Listener {
  private def onClick(event: InventoryClickEvent, menu: Menu): Unit = {
    if (event.isLeftClick || event.isRightClick) {
      menu.buttons get event.getRawSlot match {
        case Some(button) =>
          button.action apply Click(
            event.getWhoClicked.getUniqueId,
            event.isLeftClick,
            event.isShiftClick
          )
        case None =>
      }
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def onClick(event: InventoryClickEvent): Unit = {
    if (event.getInventory.getHolder.isInstanceOf[SpigotMenuHolder]) {
      event setCancelled true
      menuService
        .getMenu(event.getWhoClicked.getUniqueId)
        .map { onClick(event, _) }
    }
  }
}
