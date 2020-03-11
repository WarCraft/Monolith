package gg.warcraft.monolith.spigot.core.command

import java.util.UUID

import gg.warcraft.monolith.api.core.command.CommandService
import gg.warcraft.monolith.api.core.event.EventService
import org.bukkit.Server

class SpigotCommandService(
    implicit server: Server,
    eventService: EventService
) extends CommandService {
  override def executeCommand(playerId: UUID, label: String, args: String*): Unit = {
    val player = server.getPlayer(playerId)
    if (player != null) {
      val command = s"$label ${args.mkString(" ")}"
      server.dispatchCommand(player, command)
    }
  }
}
