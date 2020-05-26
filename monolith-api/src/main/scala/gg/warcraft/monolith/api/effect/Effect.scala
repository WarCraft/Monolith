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

import gg.warcraft.monolith.api.core.{Cancellable, Duration}
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.world.Location

class Effect(
    location: () => Location,
    period: Duration = 1.ticks
)(implicit
    taskService: TaskService
) extends Runnable
    with Cancellable {
  private val task = taskService.runTask(period, run)

  private var renderers: List[EffectRenderer] = Nil

  def tick(): Unit = {}

  final def addRenderer(renderer: EffectRenderer): Unit =
    renderers ::= renderer

  final def clearRenderers(): Unit =
    renderers = Nil

  final def cancel(delay: Duration): Unit =
    taskService.runLater(delay, cancel)

  final override def cancel(): Unit =
    task.cancel()

  final override def run(): Unit = {
    tick()
    renderers.foreach { _.render(location()) }
  }
}
