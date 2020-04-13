package gg.warcraft.monolith.spigot.core.task

import gg.warcraft.monolith.api.core.task.{Task, TaskService}
import gg.warcraft.monolith.api.core.Duration
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.{BukkitRunnable, BukkitTask}

class SpigotTaskService(implicit plugin: Plugin) extends TaskService {
  private final val immediately = Duration.immediately.inTicks

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
    schedule(task, _.runTaskTimer(plugin, immediately, period.ticks))

  override def runTaskAsync(period: Duration, task: () => Unit): Task =
    schedule(task, _.runTaskTimerAsynchronously(plugin, immediately, period.ticks))
}
