package gg.warcraft.monolith.spigot.core.auth

import java.util.UUID

import gg.warcraft.monolith.api.core.auth.Principal
import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.entity.Equipment

case object Console extends Principal {
  override val id: UUID = null
  override val name: String = "Console"
  override val equipment: Equipment = Equipment()

  override def isPlayer: Boolean = false
  override def hasPermission(permission: String): Boolean = true

  override def sendMessage(message: Message): Unit = println(message.original)
}
