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
