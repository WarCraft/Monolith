package gg.warcraft.monolith.spigot.menu

import java.util.UUID

import gg.warcraft.monolith.api.core.TaskService
import gg.warcraft.monolith.api.menu.{Menu, MenuService}
import javax.inject.Inject
import org.bukkit.Server

import scala.collection.mutable

private object SpigotMenuService {
  private val menus = mutable.Map.empty[UUID, Menu]
}

class SpigotMenuService @Inject() (
    private val server: Server,
    private val taskService: TaskService,
    private val menuMapper: SpigotMenuMapper
) extends MenuService {
  import SpigotMenuService.menus

  override def getMenu(viewerId: UUID): Option[Menu] =
    menus.get(viewerId)

  override def showMenu(viewerId: UUID, menu: Menu): Unit = {
    val inventory = menuMapper.map(menu, viewerId)
    taskService.runNextTick(() => {
      val player = server.getPlayer(viewerId)
      if (player != null) player.openInventory(inventory)
      else menus.remove(viewerId)
    })
    menus.put(viewerId, menu)
  }

  override def closeMenu(viewerId: UUID): Unit = {
    taskService.runNextTick(() => {
      val player = server.getPlayer(viewerId)
      if (player != null) player.closeInventory()
    })
    menus.remove(viewerId)
  }
}
