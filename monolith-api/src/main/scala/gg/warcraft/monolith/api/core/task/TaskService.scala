package gg.warcraft.monolith.api.core.task

import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.core.Duration._

trait TaskService {
  def evalLater(delay: Duration, task: => Unit): Task
  def evalLater(delay: Duration)(task: => Unit): Task = evalLater(delay, task)
  def runLater(delay: Duration, task: () => Unit): Task = evalLater(delay, task())
  def evalNextTick(task: => Unit): Task = evalLater(1.ticks)(task)
  def runNextTick(task: () => Unit): Task = evalLater(1.ticks)(task())

  def evalAsync(task: => Unit): Task
  def runAsync(task: () => Unit): Task = evalAsync(task())

  def runTask(period: Duration, task: () => Unit): Task
  def runEachTick(task: () => Unit): Task = runTask(1.ticks, task)

  def runTaskAsync(period: Duration, task: () => Unit): Task
}
