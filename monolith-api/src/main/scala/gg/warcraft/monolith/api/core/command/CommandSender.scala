package gg.warcraft.monolith.api.core.command

import java.util.UUID

trait CommandSender {
  val name: String
  val playerId: Option[UUID]
  val isPlayer: Boolean = playerId.isDefined
  val isConsole: Boolean = playerId.isEmpty
}
