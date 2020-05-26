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

package gg.warcraft.monolith.spigot

import java.util.logging.Logger

import gg.warcraft.monolith.api.core.MonolithPlugin
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.spigot.core.task.SpigotTaskService
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.event.{HandlerList, Listener}
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.Plugin
import org.bukkit.Server

abstract class SpigotMonolithPlugin extends JavaPlugin with MonolithPlugin {
  protected implicit lazy val _server: Server = getServer
  protected implicit lazy val _plugin: Plugin = this
  protected implicit lazy val _logger: Logger = getLogger

  protected implicit lazy val eventService: EventService =
    new EventService.Wrapper(implicits.monolithEventService)
  protected implicit lazy val taskService: TaskService = new SpigotTaskService

  override def onLoad(): Unit = saveDefaultConfig()

  override def onDisable(): Unit = {
    HandlerList.unregisterAll(this)
    eventService.asInstanceOf[EventService.Wrapper].unsubscribeAll()
    databases.foreach { _.close() }
  }

  override def onCommand(
      sender: CommandSender,
      command: Command,
      label: String,
      args: Array[String]
  ): Boolean = {
    // TODO call command service
    false
  }

  protected def subscribe(listener: Listener): Unit =
    getServer.getPluginManager.registerEvents(listener, this)

  protected def unsubscribe(listener: Listener): Unit =
    HandlerList.unregisterAll(listener)
}
