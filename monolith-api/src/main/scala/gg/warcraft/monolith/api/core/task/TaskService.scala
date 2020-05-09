package gg.warcraft.monolith.api.core.task

import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.core.Duration._

trait TaskService {
  // eval
  def evalLater(delay: Duration, task: => Unit): Task
  def evalAsync(task: => Unit): Task

  // run
  def runLater(delay: Duration, task: () => Unit): Task = evalLater(delay, task())
  def runAsync(task: () => Unit): Task = evalAsync(task())

  // task
  def runTask(period: Duration, task: () => Unit): Task
  def runTaskAsync(period: Duration, task: () => Unit): Task

  // tick
  def evalNextTick(task: => Unit): Task = evalLater(1.ticks, task)
  def runNextTick(task: () => Unit): Task = evalLater(1.ticks, task())
  def runEachTick(task: () => Unit): Task = runTask(1.ticks, task)
}
