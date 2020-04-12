package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.core.{Cancellable, Duration}
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.world.Location

class Effect(
    location: () => Location,
    period: Duration = Duration.oneTick,
    tick: () => Unit = () => {}
)(
    implicit taskService: TaskService
) extends Runnable
    with Cancellable {
  private val task = taskService runTask (period, run)

  private var renderers: List[EffectRenderer] = Nil

  def addRenderer(renderer: EffectRenderer): Unit =
    renderers ::= renderer

  def clearRenderers(): Unit =
    renderers = Nil

  def cancel(delay: Duration): Unit =
    taskService runLater (delay, cancel)

  override def cancel(): Unit =
    task.cancel()

  override def run(): Unit = {
    tick()
    renderers foreach { _ render location() }
  }
}
