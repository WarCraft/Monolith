package gg.warcraft.monolith.spigot.menu

import gg.warcraft.monolith.api.menu.{Menu, MenuService}
import gg.warcraft.monolith.app.menu.SimpleClick
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.event.inventory.InventoryClickEvent

class SpigotMenuEventMapper(
    implicit private val menuService: MenuService
) extends Listener {
  private def onClick(event: InventoryClickEvent, menu: Menu): Unit = {
    if (event.isLeftClick || event.isRightClick) {
      val button = menu.getButtons.get(event.getRawSlot)
      if (button != null) {
        val click = new SimpleClick(
          event.getWhoClicked.getUniqueId,
          event.isLeftClick,
          event.isShiftClick
        )
        button.getAction.accept(click)
      }
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def onClick(event: InventoryClickEvent): Unit = {
    if (event.getInventory.getHolder.isInstanceOf[MonolithMenuHolder]) {
      event.setCancelled(true)
      menuService
        .getMenu(event.getWhoClicked.getUniqueId)
        .map(onClick(event, _))
    }
  }
}
