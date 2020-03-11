package gg.warcraft.monolith.api.core.command

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.core.auth.Principal

object Command {
  sealed trait Result

  final case object success extends Result
  final case class success(messages: List[Message]) extends Result

  final case object invalid extends Result
  final case object consoleOnly extends Result
  final case object playersOnly extends Result

  trait Handler {
    def handle(executor: Principal, command: Command, args: String*): Command.Result
  }

  def apply(name: String, aliases: List[String], usage: String): Command = {
    val usageMessage = Message.server(usage)
    Command(name, aliases, Some(usageMessage))
  }
}

case class Command(
    name: String,
    aliases: List[String],
    usage: Option[Message] = None
)
