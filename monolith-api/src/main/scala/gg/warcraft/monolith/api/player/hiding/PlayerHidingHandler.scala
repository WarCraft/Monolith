package gg.warcraft.monolith.api.player.hiding

import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.player.{
  PlayerConnectEvent, PlayerDisconnectEvent, PlayerPermissionsChangedEvent
}

class PlayerHidingHandler(implicit
    service: PlayerHidingService
) extends Event.Handler {
  import service._

  override def handle(event: Event): Unit = event match {
    case PlayerConnectEvent(player)               => hideAllFrom(player.id)
    case PlayerDisconnectEvent(player)            => revealAllTo(player.id)
    case PlayerPermissionsChangedEvent(player, _) => updateHideFrom(player.id)
    case _                                        =>
  }
}
