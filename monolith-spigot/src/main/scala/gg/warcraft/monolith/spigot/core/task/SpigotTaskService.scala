package gg.warcraft.monolith.spigot.core.task

import gg.warcraft.monolith.api.core.task.{Task, TaskService}
import gg.warcraft.monolith.api.core.Duration
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.{BukkitRunnable, BukkitTask}

class SpigotTaskService(implicit plugin: Plugin) extends TaskService {
  private final val immediately = Duration.immediately.inTicks

  private def schedule(
      task: => Unit,
      scheduler: BukkitRunnable => BukkitTask
  ): Task = {
    val bukkitTask = scheduler(() => task)
    new SpigotTask(bukkitTask)
  }

  override def runLater(delay: Duration, task: => Unit): Task =
    schedule(task, _.runTaskLater(plugin, delay.inTicks))

  override def runAsync(task: => Unit): Task =
    schedule(task, _.runTaskAsynchronously(plugin))

  override def runNextTick(task: => Unit): Unit =
    runLater(Duration.oneTick, task)

  override def runTask(period: Duration, task: => Unit): Task =
    schedule(task, _.runTaskTimer(plugin, immediately, period.inTicks))

  override def runTaskAsync(period: Duration, task: => Unit): Task =
    schedule(task, _.runTaskTimerAsynchronously(plugin, immediately, period.inTicks))

  override def runEachTick(task: => Unit): Task =
    runTask(Duration.oneTick, task)
}
