package gg.warcraft.monolith.api.core.command

import java.util.UUID

trait CommandSender {
  val name: String
  val playerId: Option[UUID]

  def isPlayer: Boolean = playerId.isDefined
  def isConsole: Boolean = playerId.isEmpty
}
