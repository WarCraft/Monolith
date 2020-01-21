package gg.warcraft.monolith.api.core.command

import java.util.UUID

object CommandSender {
  val console: CommandSender = CommandSender("Server", None)
}

case class CommandSender(
    name: String,
    playerId: Option[UUID]
) {
  def isPlayer: Boolean = playerId.isDefined
  def isConsole: Boolean = playerId.isEmpty
}
