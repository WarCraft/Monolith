package gg.warcraft.monolith.api.player.hiding

import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.player.{
  PlayerConnectEvent, PlayerDisconnectEvent, PlayerPermissionsChangedEvent
}

class PlayerHidingHandler(service: PlayerHidingService) extends Event.Handler {
  override def handle(event: Event): Unit = {
    case it: PlayerConnectEvent            => service hideAllFrom it.playerId
    case it: PlayerDisconnectEvent         => service revealAllTo it.playerId
    case it: PlayerPermissionsChangedEvent => service updateHideFrom it.playerId
    case _                                 =>
  }
}
