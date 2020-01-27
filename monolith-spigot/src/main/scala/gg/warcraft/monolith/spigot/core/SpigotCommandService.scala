package gg.warcraft.monolith.spigot.core

import java.util.UUID

import gg.warcraft.monolith.api.core.command.CommandService
import org.bukkit.Server

class SpigotCommandService(
    implicit server: Server
) extends CommandService {
  private val consoleSender = server.getConsoleSender

  override def dispatchCommand(playerId: UUID, cmd: String, args: String*): Unit = {
    val player = server.getPlayer(playerId)
    if (player != null) player.performCommand(s"$cmd ${args.mkString(" ")}")
  }

  override def dispatchConsoleCommand(cmd: String, args: String*): Unit =
    server.dispatchCommand(consoleSender, s"${cmd} ${args.mkString(" ")}")
}
