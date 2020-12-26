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

package gg.warcraft.monolith.spigot.menu

import gg.warcraft.monolith.api.menu.{Button, Click, Menu, MenuService}
import gg.warcraft.monolith.api.player.PlayerService
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class SpigotMenuHandler(implicit
    menuService: MenuService,
    playerService: PlayerService
) extends Listener {
  private def onClick(event: InventoryClickEvent, menu: Menu): Unit = {
    if (event.isLeftClick || event.isRightClick) {
      menu.buttons.get(event.getRawSlot) match {
        case Some(button) =>
          val player = playerService.getPlayer(event.getWhoClicked.getUniqueId)
          val click = Click(player, event.isLeftClick, event.isShiftClick)
          button.action.apply(click) match {
            case Button.noop =>
            case Button.closeMenu =>
              menuService.closeMenu(player.id)
            case Button.closeMenu(message) =>
              menuService.closeMenu(player.id)
              playerService.sendMessage(player.id, message)
            case Button.updateMenu(menu) =>
              menuService.showMenu(player.id, menu(player))
            case Button.updateButton(button) =>
              val updatedButtons = menu.buttons + (event.getRawSlot -> button)
              val updatedMenu = menu.copy(buttons = updatedButtons)
              menuService.showMenu(player.id, updatedMenu)
          }
        case None =>
      }
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def onClick(event: InventoryClickEvent): Unit = {
    if (event.getInventory.getHolder.isInstanceOf[SpigotMenuHolder]) {
      event.setCancelled(true)
      menuService
        .menus.get(event.getWhoClicked.getUniqueId)
        .map { onClick(event, _) }
    }
  }
}
