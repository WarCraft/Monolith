package gg.warcraft.monolith.api.core.command

import java.util.UUID

object ConsoleCommandSender extends CommandSender {
  val name = "Server"
  val playerId: Option[UUID] = None
}
