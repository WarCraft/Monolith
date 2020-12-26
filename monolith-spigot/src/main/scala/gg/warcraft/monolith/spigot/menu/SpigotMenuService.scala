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

import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.menu.{Menu, MenuService}
import org.bukkit.Server

import java.util.UUID

class SpigotMenuService(
    implicit
    server: Server,
    taskService: TaskService,
    menuMapper: SpigotMenuMapper
) extends MenuService {
  private var _menus: Map[UUID, Menu] = Map.empty
  def menus: Map[UUID, Menu] = _menus

  override def showMenu(viewerId: UUID, menu: Menu): Unit = {
    val inventory = menuMapper.map(menu, viewerId)
    taskService.runNextTick(() => {
      val player = server.getPlayer(viewerId)
      if (player != null) player.openInventory(inventory)
      else _menus -= viewerId
    })
    _menus += (viewerId -> menu)
  }

  override def closeMenu(viewerId: UUID): Unit = {
    taskService.runNextTick(() => {
      val player = server.getPlayer(viewerId)
      if (player != null) player.closeInventory()
    })
    _menus -= viewerId
  }
}
