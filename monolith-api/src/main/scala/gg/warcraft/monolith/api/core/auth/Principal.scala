package gg.warcraft.monolith.api.core.auth

import java.util.UUID

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.entity.Equipment

// TODO add isPlayer isConsole checks and allow casting to Player
// TODO or add non option id to principal and set console to random every server start?
trait Principal {
  val principalId: Option[UUID]
  def name: String
  def equipment: Equipment
  def hasPermission(permission: String): Boolean
  def sendMessage(message: Message): Unit
}
