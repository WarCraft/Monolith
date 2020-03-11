package gg.warcraft.monolith.api.core.auth

import java.util.UUID

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.world.item.ItemType

trait Principal {
  val name: String
  val playerId: Option[UUID]
  val offhand: Option[ItemType]
  def isConsole: Boolean
  def hasPermission(permission: String): Boolean
  def sendMessage(message: Message): Unit
}
