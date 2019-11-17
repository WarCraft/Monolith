package gg.warcraft.monolith.api.menu

import java.util.UUID

trait MenuService {
  def getMenu(viewerId: UUID): Option[Menu]
  def showMenu(viewerId: UUID, menu: Menu): Unit
  def closeMenu(viewerId: UUID): Unit
}
