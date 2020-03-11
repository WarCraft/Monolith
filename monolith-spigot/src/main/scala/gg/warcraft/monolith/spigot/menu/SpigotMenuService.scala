package gg.warcraft.monolith.spigot.menu

import java.util.UUID

import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.menu.{Menu, MenuService}
import org.bukkit.Server

class SpigotMenuService(
    implicit server: Server,
    taskService: TaskService,
    menuMapper: SpigotMenuMapper
) extends MenuService {
  private var menus: Map[UUID, Menu] = Map.empty

  override def getMenu(viewerId: UUID): Option[Menu] =
    menus.get(viewerId)

  override def showMenu(viewerId: UUID, menu: Menu): Unit = {
    val inventory = menuMapper map (menu, viewerId)
    taskService.runNextTick(() => {
      val player = server getPlayer viewerId
      if (player != null) player openInventory inventory
      else menus -= viewerId
    })
    menus += (viewerId -> menu)
  }

  override def closeMenu(viewerId: UUID): Unit = {
    taskService.runNextTick(() => {
      val player = server getPlayer viewerId
      if (player != null) player.closeInventory()
    })
    menus -= viewerId
  }
}
