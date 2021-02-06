/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.core.command

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.core.auth.Principal

import java.util.UUID
import scala.util.chaining._

abstract class CommandService {
  private final val ERR_COMMAND_CONSOLE_ONLY =
    Message.server("This command can only be executed from the console.")
  private final val ERR_COMMAND_PLAYERS_ONLY =
    Message.server("This command can only be executed by players.")

  private var _commands: Map[String, Command] = Map.empty
  private var _handlers: Map[Command, Command.Handler] = Map.empty

  def commands: Map[String, Command] = _commands

  def executeCommand(label: String, args: String*): Unit

  def executeCommand(playerId: UUID, label: String, args: String*): Unit

  def registerCommand(command: Command, handler: Command.Handler): Unit = {
    val aliases = command.name.toLowerCase :: command.aliases.map { _.toLowerCase }
    val duplicates = aliases.intersect(_commands.keys.toSeq)
    if (duplicates.isEmpty) {
      aliases.foreach(it => _commands += (it -> command))
      _handlers += (command -> handler)
    } else throw CommandAlreadyExists(duplicates)
  }

  def deregisterCommand(command: Command): Unit = {
    (command.name :: command.aliases).foreach { _commands -= _.toLowerCase }
    _handlers -= command
  }

  private[monolith] def processCommand(
      principal: Principal,
      label: String,
      args: List[String]
  ): Command.Result = {
    val command = _commands(label)
    _handlers(command).handle(principal, command, args: _*)
      .tap {
        case Command.success           =>
        case Command.success(messages) => messages.foreach { principal.sendMessage }
        case Command.consoleOnly       => principal.sendMessage(ERR_COMMAND_CONSOLE_ONLY)
        case Command.playersOnly       => principal.sendMessage(ERR_COMMAND_PLAYERS_ONLY)
        case Command.invalid           => command.usage.foreach { principal.sendMessage }
      }
  }
}
