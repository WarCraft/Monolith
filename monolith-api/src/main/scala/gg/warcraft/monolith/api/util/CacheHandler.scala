package gg.warcraft.monolith.api.util

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}
import gg.warcraft.monolith.api.util.chaining._

class CacheHandler(
    load: UUID => Unit,
    invalidate: UUID => Unit
) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case PlayerDisconnectEvent(player) => player.id |> invalidate
    case _                             =>
  }

  override def reduce[T <: PreEvent](event: T): T = event match {
    case PlayerPreConnectEvent(playerId, _, _, _) => playerId |> load; event
    case _                                        => event
  }
}
