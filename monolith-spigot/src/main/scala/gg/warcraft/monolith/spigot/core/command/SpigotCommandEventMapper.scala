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

import gg.warcraft.monolith.api.core.auth.Principal
import gg.warcraft.monolith.api.core.command.{CommandPreExecuteEvent, CommandService}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.player.PlayerService
import gg.warcraft.monolith.spigot.core.auth.Console
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.server.ServerCommandEvent

class SpigotCommandEventMapper(implicit
    commandService: CommandService,
    eventService: EventService,
    playerService: PlayerService
) extends Listener {
  private def handle(
      principal: Principal,
      label: String,
      args: List[String],
      cancelled: Boolean
  ): Boolean = commandService.commands.get(label) match {
    case Some(command) =>
      var preEvent = CommandPreExecuteEvent(principal, command, args, cancelled)
      preEvent = eventService.publish(preEvent)
      preEvent.allowed
    case None => true
  }

  @EventHandler
  def handle(event: PlayerCommandPreprocessEvent): Unit = {
    val principal = playerService.getPlayer(event.getPlayer.getUniqueId)
    val label :: args = event.getMessage
      .substring(1)
      .split(" ").toList
    val allowed = handle(principal, label, args, event.isCancelled)
    event.setCancelled(!allowed)
  }

  @EventHandler
  def handle(event: ServerCommandEvent): Unit = {
    val label :: args = event.getCommand.split(" ").toList
    val allowed = handle(Console, label, args, event.isCancelled)
    event.setCancelled(!allowed)
  }
}
