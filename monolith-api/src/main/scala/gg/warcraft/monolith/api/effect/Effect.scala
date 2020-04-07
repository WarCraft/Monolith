package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.core.{Cancellable, Duration}
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.world.Location

class Effect(
    location: => Location,
    period: Duration = Duration.oneTick,
    onRun: => Unit = Unit
)(
    implicit taskService: TaskService
) extends Runnable
    with Cancellable {
  private val task = taskService runTask (period, run)

  private var renderers: List[EffectRenderer] = Nil

  def add(renderer: EffectRenderer): Unit =
    renderers ::= renderer

  def clear(): Unit =
    renderers = Nil

  override def run(): Unit = {
    onRun
    renderers foreach { _ render location }
  }

  override def cancel(): Unit =
    task.cancel()

  def cancel(delay: Duration): Unit =
    taskService runLater (delay, cancel)
}
