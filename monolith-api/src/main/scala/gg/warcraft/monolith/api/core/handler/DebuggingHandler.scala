package gg.warcraft.monolith.api.core.handler

import gg.warcraft.monolith.api.core.auth.AuthService
import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.entity.{EntityInteractEvent, EntityService}

class DebuggingHandler(implicit
    authService: AuthService,
    entityService: EntityService
) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case EntityInteractEvent(entity, player, _) =>
      if (authService.isDebugging(player)) entityService.removeEntity(entity.id)
    case _ =>
  }
}
