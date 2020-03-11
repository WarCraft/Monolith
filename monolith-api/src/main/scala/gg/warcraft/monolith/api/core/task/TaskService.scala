package gg.warcraft.monolith.api.core.task

import gg.warcraft.monolith.api.core.Duration

trait TaskService {
  def runLater(delay: Duration, task: => Unit): Task
  def runAsync(task: => Unit): Task
  def runNextTick(task: => Unit)

  def runTask(period: Duration, task: => Unit): Task
  def runTaskAsync(period: Duration, task: => Unit): Task
  def runEachTick(task: => Unit): Task
}
