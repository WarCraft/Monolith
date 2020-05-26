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

package gg.warcraft.monolith.api.entity.status

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{Event, EventService}
import gg.warcraft.monolith.api.core.task.TaskService

import scala.collection.mutable

class StatusService(
    implicit eventService: EventService,
    taskService: TaskService
) extends Event.Handler {
  private val statuses = mutable.Map[UUID, Status]()

  private[status] def loadStatus(playerId: UUID): Unit =
    statuses put (playerId, new Status)

  private[status] def invalidateStatus(entityId: UUID): Unit =
    statuses remove entityId

  def getStatus(entityId: UUID): Status =
    statuses.getOrElseUpdate(entityId, new Status)

  def addEffect(entityId: UUID, effects: Status.Effect*): Unit = {
    val status = getStatus(entityId)
    effects.foreach { effect =>
      if (!status.effects.contains(effect)) {
        val newStatus = status.copy(effects = status.effects + effect)
        statuses.put(entityId, newStatus)

        effect.duration.foreach {
          taskService.evalLater(_, removeEffect(entityId, effect))
        }

        val event = StatusEffectGainedEvent(entityId, effect)
        eventService.publish(event)
      }
    }
  }

  def removeEffect(entityId: UUID, effects: Status.Effect*): Unit = {
    val status = getStatus(entityId)
    effects.foreach { effect =>
      if (status.effects contains effect) {
        val newStatus = status.copy(effects = status.effects - effect)
        statuses.put(entityId, newStatus)

        val event = StatusEffectLostEvent(entityId, effect)
        eventService.publish(event)
      }
    }
  }
}
