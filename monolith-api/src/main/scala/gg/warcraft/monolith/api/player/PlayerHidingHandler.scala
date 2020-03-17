package gg.warcraft.monolith.api.player

import gg.warcraft.monolith.api.core.event.Event

class PlayerHidingHandler(hidingService: PlayerHidingService) extends Event.Handler {
  override def handle(event: Event): Unit = {
    case it: PlayerConnectEvent    => hidingService.hideAllFrom(it.playerId)
    case it: PlayerDisconnectEvent => hidingService.revealAllTo(it.playerId)

    case it: PlayerPermissionsChangedEvent =>
      hidingService.updateHideFrom(it.playerId)

    case _ =>
  }
}
