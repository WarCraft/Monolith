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

package gg.warcraft.monolith.spigot.core.command

import gg.warcraft.monolith.api.core.command.{Command, CommandService}
import org.bukkit.Server
import org.bukkit.command.PluginCommand
import org.bukkit.plugin.Plugin

import java.lang.reflect.Constructor
import java.util.UUID
import scala.jdk.CollectionConverters._

class SpigotCommandService(implicit
    server: Server,
    plugin: Plugin
) extends CommandService {
  private val pluginCommandConstructor: Constructor[PluginCommand] =
    classOf[PluginCommand]
      .getDeclaredConstructors()(0)
      .asInstanceOf[Constructor[PluginCommand]]
  pluginCommandConstructor.setAccessible(true)

  override def executeCommand(label: String, args: String*): Unit = {
    val command = s"$label ${args.mkString(" ")}"
    server.dispatchCommand(server.getConsoleSender, command)
  }

  override def executeCommand(playerId: UUID, label: String, args: String*): Unit = {
    val player = server.getPlayer(playerId)
    if (player != null) {
      val command = s"$label ${args.mkString(" ")}"
      server.dispatchCommand(player, command)
    }
  }

  override def registerCommand(command: Command, handler: Command.Handler): Unit = {
    super.registerCommand(command, handler)
    val pluginCommand = pluginCommandConstructor.newInstance(command.name, plugin)
    pluginCommand.setAliases(command.aliases.asJava)
    command.usage.foreach { it => pluginCommand.setUsage(it.formatted) }
    server.getCommandMap.register(plugin.getName, pluginCommand)
  }

  override def deregisterCommand(command: Command): Unit = {
    super.deregisterCommand(command)
    server.getCommandMap.getCommand(command.name).unregister(server.getCommandMap)
  }
}
