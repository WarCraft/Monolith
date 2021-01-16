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

package gg.warcraft.monolith.spigot.core

import gg.warcraft.monolith.api.core.Duration.DurationOps
import gg.warcraft.monolith.api.core.MonolithConfig
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.entity.EntityService
import org.bukkit.attribute.Attribute
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.{EventHandler, Listener}

class SpigotBaseHealthHandler(config: MonolithConfig)(implicit
    taskService: TaskService,
    entityService: EntityService
) extends Listener {
  private val baseHealth = config.baseHealth

  @EventHandler
  def on(event: PlayerJoinEvent): Unit = {
    val player = event.getPlayer
    val attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
    if (attribute.getBaseValue != baseHealth) attribute.setBaseValue(baseHealth)

    taskService.runLater(1.ticks, () => player.setHealth(player.getHealth + 1))
    taskService.runLater(2.ticks, () => player.setHealth(player.getHealth - 1))
  }
}
