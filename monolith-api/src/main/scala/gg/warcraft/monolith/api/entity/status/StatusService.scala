package gg.warcraft.monolith.api.entity.status

import java.util.UUID

import gg.warcraft.monolith.api.core.{EventService, TaskService}

import scala.collection.mutable

private object StatusService {
  private val statuses = mutable.Map.empty[UUID, Status]
  private val emptyStatus = new Status(List.empty)
}

class StatusService(
    private implicit val eventService: EventService,
    private implicit val taskService: TaskService
) {
  import StatusService._

  def getEffects(entityId: UUID): List[StatusEffect] = {

  }

  def getStatus(entityId: UUID): Status =
    statuses.getOrElse(entityId, emptyStatus)

  def addEffect(entityId: UUID, effects: StatusEffect*): Unit = {
    val status = getStatus(entityId)
    effects.foreach(effect => {
      if(!status.effects.exists(_.name == effect.name)) {

      }
    })
    val newStatus = status.copy(effect :: status.effects)
    statuses.put(entityId, newStatus)
    // TODO broadcast event

    effects.foreach(effect => {
      effect.duration match {
        case Some(it) =>
          taskService.runLater(() => removeEffect(entityId, effect), it)
        case _ => ()
      }
    })
  }

  def removeEffect(entityId: UUID, effects: StatusEffect*): Unit = {
    val status = getStatus(entityId)
    status.effects.filter
    val newEffects = status.effects.filterNot(_ == effect)
    if (status.effects.length != newEffects.length) {
      val newStatus = status.copy(newEffects)
      statuses.put(entityId, newStatus)
      // TODO broadcast event
    }
  }
}
