package gg.warcraft.monolith.api.core.handler

import gg.warcraft.monolith.api.core.auth.AuthService
import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.entity.{EntityInteractEvent, EntityService}
import gg.warcraft.monolith.api.player.PlayerService

class DebuggingHandler(
    authService: AuthService,
    entityService: EntityService,
    playerService: PlayerService
) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case EntityInteractEvent(entityId, _, playerId, _, _, _) =>
      playerService getPlayer playerId match {
        case Some(player) =>
          if (authService isDebugging player) entityService removeEntity entityId
        case None =>
      }
    case _ =>
  }
}
