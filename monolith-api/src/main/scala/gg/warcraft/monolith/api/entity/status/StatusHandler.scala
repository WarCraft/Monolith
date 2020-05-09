package gg.warcraft.monolith.api.entity.status

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.entity.{Entity, EntityDeathEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

class StatusHandler(service: StatusService) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case EntityDeathEvent(entity, _) =>
      if (entity.typed != Entity.Type.PLAYER) service.invalidateStatus(entity.id)
    case PlayerDisconnectEvent(player, _) =>
      service.invalidateStatus(player.id)
    case _ =>
  }

  override def reduce[T <: PreEvent](event: T): T = {
    event match {
      case PlayerPreConnectEvent(player, _) => service.loadStatus(player.id)
      case _                                =>
    }
    event
  }
}
