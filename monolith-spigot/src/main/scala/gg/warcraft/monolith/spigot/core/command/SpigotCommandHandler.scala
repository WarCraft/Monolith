package gg.warcraft.monolith.spigot.core.command

import gg.warcraft.monolith.api.core.command.CommandService
import gg.warcraft.monolith.api.player.PlayerService
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class SpigotCommandHandler(implicit
    commandService: CommandService,
    playerService: PlayerService
) extends Listener {
  @EventHandler
  def onCommandPreProcess(event: PlayerCommandPreprocessEvent): Unit = {
    val Array(label, args @ _*) = event.getMessage.substring(1).split(" ")
    if (commandService.commands.contains(label)) {
      val principal = playerService.getPlayer(event.getPlayer.getUniqueId)
      commandService.processCommand(principal, label, args: _*)
      event.setCancelled(true)
    }
  }
}
