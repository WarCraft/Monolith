package gg.warcraft.monolith.api.core.command

trait CommandEvent

// TODO fire these events
case class CommandPreExecutedEvent(
    sender: CommandSender,
    cmd: String,
    label: String,
    args: String*
)

case class CommandExecutedEvent(
    sender: CommandSender,
    cmd: String,
    label: String,
    args: String*
)
