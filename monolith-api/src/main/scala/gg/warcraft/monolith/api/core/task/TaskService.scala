package gg.warcraft.monolith.api.core.task

import gg.warcraft.monolith.api.core.Duration

trait TaskService {
  def evalLater(delay: Duration, task: => Unit): Task
  def runLater(delay: Duration, task: () => Unit): Task = evalLater(delay, task())
  def evalNextTick(task: => Unit): Task = evalLater(Duration.oneTick, task)
  def runNextTick(task: () => Unit): Task = evalLater(Duration.oneTick, task())

  def evalAsync(task: => Unit): Task
  def runAsync(task: () => Unit): Task = evalAsync(task())

  def runTask(period: Duration, task: () => Unit): Task
  def runEachTick(task: () => Unit): Task = runTask(Duration.oneTick, task)

  def runTaskAsync(period: Duration, task: () => Unit): Task
}
