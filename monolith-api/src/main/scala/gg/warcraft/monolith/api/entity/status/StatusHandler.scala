package gg.warcraft.monolith.api.entity.status

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

class StatusHandler(service: StatusService) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case it: PlayerDisconnectEvent => service loadStatus it.playerId
    case _                         =>
  }

  override def reduce[T <: PreEvent](event: T): T = event match {
    case it: PlayerPreConnectEvent => service invalidateStatus it.playerId; event
    case _                         => event
  }
}
