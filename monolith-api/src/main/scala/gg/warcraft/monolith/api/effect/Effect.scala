/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.core.{Duration, Playable}
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.task.{Task, TaskService}
import gg.warcraft.monolith.api.world.Location

abstract class Effect(autoStart: Boolean = true)(implicit
    taskService: TaskService
) extends Runnable
    with Playable {
  protected val location: () => Location
  protected val period: Duration = 1.ticks

  private var renderers: List[EffectRenderer] = Nil
  private var task: Task = _

  if (autoStart) start()

  def tick(): Unit = {}

  final def addRenderer(renderer: EffectRenderer): Unit =
    renderers ::= renderer

  final def clearRenderers(): Unit =
    renderers = Nil

  final override def start(): Unit =
    task = taskService.runTask(period, run)

  final override def run(): Unit = {
    tick()
    renderers.foreach { _.render(location()) }
  }

  final override def stop(): Unit =
    task.cancel()

  final def stop(delay: Duration): Unit =
    taskService.runLater(delay, stop)
}
