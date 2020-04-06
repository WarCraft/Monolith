package gg.warcraft.monolith.api.core.handler

import gg.warcraft.monolith.api.core.auth.AuthService
import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.entity.{EntityInteractEvent, EntityService}

class DebuggingHandler(
    authService: AuthService,
    entityService: EntityService
) extends Event.Handler {
  override def handle(event: Event): Unit = {
    case event: EntityInteractEvent =>
      if (authService.isDebugging(event.player))
        entityService.removeEntity(event.entity)
    case _ =>
  }
}
