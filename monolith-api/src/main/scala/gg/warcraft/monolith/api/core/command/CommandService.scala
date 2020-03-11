package gg.warcraft.monolith.api.core.command

import java.util.UUID

import gg.warcraft.monolith.api.core.Principal
import gg.warcraft.monolith.api.core.event.EventService

abstract class CommandService(implicit eventService: EventService) {
  private var _commands: Map[String, Command] = Map.empty
  private var _handlers: Map[Command, Command.Handler] = Map.empty

  def commands: Map[String, Command] = _commands

  def executeCommand(playerId: UUID, label: String, args: String*): Unit

  def registerCommand(command: Command, handler: Command.Handler): Unit = {
    val aliases = command.name.toLowerCase :: (command.aliases map { _.toLowerCase })
    val duplicates = aliases intersect _commands.keys.toSeq
    if (duplicates.isEmpty) {
      aliases foreach (it => _commands += (it -> command))
      _handlers += (command -> handler)
    } else throw CommandAlreadyExists(duplicates)
  }

  def deregisterAlias(alias: String): Unit =
    _commands -= alias

  def deregisterCommand(command: Command): Unit = {
    (command.name :: command.aliases) foreach { _commands -= _.toLowerCase }
    _handlers -= command
  }

  private[monolith] def processCommand(
      principal: Principal,
      label: String,
      args: String*
  ): Unit = {
    val command = _commands(label)
    var preEvent = CommandPreExecuteEvent(principal, command, args.toList)
    preEvent = eventService publish preEvent
    if (preEvent.allowed) {
      _handlers(command) handle (principal, command, args: _*)
      eventService publish CommandExecuteEvent(principal, command, args.toList)
    }
  }
}
