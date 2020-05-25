package gg.warcraft.monolith.api.entity.data

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.entity.{EntityDeathEvent, EntityPreSpawnEvent}

class EntityDataCacheHandler(implicit
    service: EntityDataService
) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case EntityDeathEvent(entity, _) => service.deleteEntityData(entity.id)
    case _                           =>
  }

  override def handle(event: PreEvent): Unit = event match {
    case EntityPreSpawnEvent(entity, _, _, _) =>
      if (!service.data.contains(entity.id))
        service.setEntityData(EntityData(entity.id))
    case _ =>
  }
}
