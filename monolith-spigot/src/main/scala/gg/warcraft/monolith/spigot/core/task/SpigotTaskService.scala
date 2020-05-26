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

package gg.warcraft.monolith.spigot.core.task

import gg.warcraft.monolith.api.core.task.{Task, TaskService}
import gg.warcraft.monolith.api.core.Duration
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.{BukkitRunnable, BukkitTask}

class SpigotTaskService(implicit plugin: Plugin) extends TaskService {
  private def schedule(
      task: () => Unit,
      scheduler: BukkitRunnable => BukkitTask
  ): Task = {
    val bukkitTask = scheduler(() => task())
    new SpigotTask(bukkitTask)
  }

  override def evalLater(delay: Duration, task: => Unit): Task =
    schedule(() => task, _.runTaskLater(plugin, delay.ticks))

  override def evalAsync(task: => Unit): Task =
    schedule(() => task, _.runTaskAsynchronously(plugin))

  override def runTask(period: Duration, task: () => Unit): Task =
    schedule(task, _.runTaskTimer(plugin, 0, period.ticks))

  override def runTaskAsync(period: Duration, task: () => Unit): Task =
    schedule(task, _.runTaskTimerAsynchronously(plugin, 0, period.ticks))
}
