package gg.warcraft.monolith.spigot.core.auth

import java.util.UUID

import gg.warcraft.monolith.api.core.auth.Principal
import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.item.ItemType

case object Console extends Principal {
  override val name: String = "Console"
  override val playerId: Option[UUID] = None
  override val offhand: Option[ItemType] = None
  override def isConsole: Boolean = true
  override def hasPermission(permission: String): Boolean = true
  override def sendMessage(message: Message): Unit = ???
}
