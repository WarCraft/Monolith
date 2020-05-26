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
