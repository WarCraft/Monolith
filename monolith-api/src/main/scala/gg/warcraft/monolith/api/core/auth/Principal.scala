package gg.warcraft.monolith.api.core.auth

import java.util.UUID

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.entity.Equipment

trait Principal {
  val id: UUID
  def name: String
  def equipment: Equipment

  def isPlayer: Boolean
  def hasPermission(permission: String): Boolean

  def sendMessage(message: Message): Unit
}
