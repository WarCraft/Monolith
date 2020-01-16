package gg.warcraft.monolith.api.core.command

import java.util.UUID

trait CommandService {
  def dispatchCommand(playerId: UUID, command: String, args: String*): Unit
  def dispatchConsoleCommand(command: String, args: String*): Unit
}
