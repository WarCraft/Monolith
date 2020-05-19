package gg.warcraft.monolith.api.player.currency

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

private[monolith] class CurrencyCacheHandler(implicit
    service: CurrencyService
) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case PlayerDisconnectEvent(player) => service.invalidateCurrencies(player.id)
    case _                             =>
  }

  override def reduce[T <: PreEvent](event: T): T = event match {
    case PlayerPreConnectEvent(playerId, _, _, _) =>
      service.loadCurrencies(playerId)
      event
    case _ => event
  }
}
