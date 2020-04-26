package gg.warcraft.monolith.api.entity.status

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.entity.{Entity, EntityDeathEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

class StatusHandler(service: StatusService) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case it: EntityDeathEvent =>
      if (it.entityType != Entity.Type.PLAYER) service invalidateStatus it.entityId
    case it: PlayerDisconnectEvent => service invalidateStatus it.playerId
    case _                         =>
  }

  override def reduce[T <: PreEvent](event: T): T = event match {
    case it: PlayerPreConnectEvent => service loadStatus it.playerId; event
    case _                         => event
  }
}
