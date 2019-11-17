package gg.warcraft.monolith.spigot.menu

import gg.warcraft.monolith.api.menu.MenuService
import gg.warcraft.monolith.app.menu.SimpleClick
import org.bukkit.event.{EventHandler, EventPriority}
import org.bukkit.event.inventory.InventoryClickEvent

class SpigotMenuEventMapper(
    implicit private val menuService: MenuService
) {
  @EventHandler(priority = EventPriority.HIGH)
  def onClick(event: InventoryClickEvent): Unit = {
    if (!event.getInventory.getHolder.isInstanceOf[MonolithMenuHolder]) return
    event.setCancelled(true)

    val clickerId = event.getWhoClicked.getUniqueId
    menuService.getMenu(clickerId) match {
      case Some(menu) =>
        val leftClick = event.isLeftClick
        if (leftClick || event.isRightClick) {
          val button = menu.getButtons.get(event.getRawSlot)
          if (button != null) {
            val click = new SimpleClick(clickerId, leftClick, event.isShiftClick)
            button.getAction.accept(click)
          }
        }
      case None => ()
    }
  }
}
