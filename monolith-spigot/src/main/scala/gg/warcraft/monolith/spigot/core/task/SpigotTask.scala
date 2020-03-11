package gg.warcraft.monolith.spigot.core.task

import gg.warcraft.monolith.api.core.task.Task
import org.bukkit.scheduler.BukkitTask

class SpigotTask(task: BukkitTask) extends Task {
  override val id: Int = task.getTaskId
  override val async: Boolean = !task.isSync
  override def cancel(): Unit = task.cancel()
  override def cancelled: Boolean = task.isCancelled
}
