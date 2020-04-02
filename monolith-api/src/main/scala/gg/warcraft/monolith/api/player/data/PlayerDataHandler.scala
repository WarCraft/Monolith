package gg.warcraft.monolith.api.player.data

import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

class PlayerDataHandler(service: PlayerDataService) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case it: PlayerPreConnectEvent => handlePreConnect(it)
    case it: PlayerDisconnectEvent => handleDisconnect(it)
    case _                         =>
  }

  private def handlePreConnect(event: PlayerPreConnectEvent): Unit = {
    val now = System.currentTimeMillis()
    val newData = service loadPlayerData event.playerId match {
      case Some(data) => data.copy(timeConnected = now, timeLastSeen = now)
      case None       => PlayerData(event.playerId, None, now, now)
    }
    service setPlayerData newData
  }

  private def handleDisconnect(event: PlayerDisconnectEvent): Unit = {
    service updatePlayerData event.playerId
    service invalidatePlayerData event.playerId
  }
}
