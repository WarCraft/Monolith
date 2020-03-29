package gg.warcraft.monolith.api.entity.status

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{Event, EventService, PreEvent}
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

import scala.collection.mutable

class StatusService(
    implicit eventService: EventService,
    taskService: TaskService
) extends Event.Handler {
  private val statuses = mutable.Map[UUID, Status]()

  def getStatus(entityId: UUID): Status =
    statuses.getOrElseUpdate(entityId, new Status)

  def addEffect(entityId: UUID, effects: Status.Effect*): Unit = {
    val status = getStatus(entityId)
    effects.foreach { effect =>
      if (!status.effects.contains(effect)) {
        val newStatus = status.copy(effects = status.effects + effect)
        statuses.put(entityId, newStatus)

        effect.duration.foreach {
          taskService.runLater(_, removeEffect(entityId, effect))
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

  override def handle(event: Event): Unit = event match {
    case it: PlayerDisconnectEvent => statuses.remove(it.playerId)
    case _                         =>
  }

  override def reduce[T <: PreEvent](event: T): T = {
    case it: PlayerPreConnectEvent =>
      statuses.put(it.playerId, new Status)
      event
    case _ =>
  }

  // TODO create/delete statuses for non-player Entities
}
