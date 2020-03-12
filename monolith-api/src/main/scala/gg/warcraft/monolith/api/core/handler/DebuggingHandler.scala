package gg.warcraft.monolith.api.core.handler

import gg.warcraft.monolith.api.core.auth.AuthService
import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.entity.service.EntityCommandService
import gg.warcraft.monolith.api.entity.EntityInteractEvent

class DebuggingHandler(
    implicit authService: AuthService,
    entityCommandService: EntityCommandService
) extends Event.Handler {
  override def handle(event: Event): Unit = {
    case event: EntityInteractEvent =>
      if (authService.isDebugging(event.player))
        entityCommandService.removeEntity(event.entity)
    case _ =>
  }
}
