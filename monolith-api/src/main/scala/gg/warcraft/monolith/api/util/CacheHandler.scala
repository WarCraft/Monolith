package gg.warcraft.monolith.api.util

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}
import gg.warcraft.monolith.api.util.Ops._

class CacheHandler(
    load: UUID => Unit,
    invalidate: UUID => Unit
) extends Event.Handler {
  override def handle(event: Event): Unit = {
    case it: PlayerDisconnectEvent => it.playerId |> invalidate
    case _                         =>
  }

  override def reduce[T <: PreEvent](event: T): T = {
    case it: PlayerPreConnectEvent => it.playerId |> load; event
    case _                         => event
  }
}
