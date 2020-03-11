package gg.warcraft.monolith.spigot.core.command

import gg.warcraft.monolith.api.core.command.CommandService
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class SpigotCommandHandler(commandService: CommandService) extends Listener {
  @EventHandler
  def onCommandPreProcess(event: PlayerCommandPreprocessEvent): Unit = {
    val Array(label, args @ _*) = event.getMessage split " "
    if (commandService.commands contains label) {
      // TODO get player principal
      commandService.processCommand(null, label, args: _*)
      event.setCancelled(true)
    }
  }
}
