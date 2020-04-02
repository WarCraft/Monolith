package gg.warcraft.monolith.api.entity.data

import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.entity.EntityDeathEvent

class EntityDataHandler(service: EntityDataService) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case it: EntityDeathEvent => service deleteEntityData it.entityId
    case _                    =>
  }
}
