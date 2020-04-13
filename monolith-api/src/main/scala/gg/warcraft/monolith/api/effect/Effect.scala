package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.core.{Cancellable, Duration}
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.world.Location

class Effect(
    location: () => Location,
    period: Duration = 1.ticks
)(
    implicit taskService: TaskService
) extends Runnable
    with Cancellable {
  private val task = taskService runTask (period, run)

  private var renderers: List[EffectRenderer] = Nil

  def tick(): Unit = {}

  final def addRenderer(renderer: EffectRenderer): Unit =
    renderers ::= renderer

  final def clearRenderers(): Unit =
    renderers = Nil

  final def cancel(delay: Duration): Unit =
    taskService runLater (delay, cancel)

  final override def cancel(): Unit =
    task.cancel()

  final override def run(): Unit = {
    tick()
    renderers foreach { _ render location() }
  }
}
