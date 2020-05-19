package gg.warcraft.monolith.api.player.data

import java.util.logging.Logger

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

class PlayerDataHandler(implicit
    logger: Logger,
    service: PlayerDataService
) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case PlayerDisconnectEvent(player) =>
      service.updatePlayerData(player.id)
      service.invalidatePlayerData(player.id)
    case _ =>
  }

  override def reduce[T <: PreEvent](event: T): T = event match {
    case PlayerPreConnectEvent(playerId, _, _, _) =>
      val now = System.currentTimeMillis
      val newData = service.loadPlayerData(playerId) match {
        case Some(data) => data.copy(timeConnected = now, timeLastSeen = now)
        case None       => PlayerData(playerId, None, now, now)
      }
      service.setPlayerData(newData)
      event
    case _ => event
  }
}
