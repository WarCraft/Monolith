package gg.warcraft.monolith.api.core.command

trait CommandHandler {
  def handle(sender: CommandSender, cmd: Command): Boolean
}
