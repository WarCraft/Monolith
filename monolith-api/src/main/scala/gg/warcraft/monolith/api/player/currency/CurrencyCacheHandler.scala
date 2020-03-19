package gg.warcraft.monolith.api.player.currency

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.player.{PlayerDisconnectEvent, PlayerPreConnectEvent}

class CurrencyCacheHandler(currencyService: CurrencyService) extends Event.Handler {
  override def handle(event: Event): Unit = {
    case it: PlayerDisconnectEvent =>
      currencyService.invalidateCurrencies(it.playerId)
    case _ =>
  }

  override def reduce[T <: PreEvent](event: T): T = {
    case it: PlayerPreConnectEvent =>
      currencyService.loadCurrencies(it.playerId)
      event
    case _ => event
  }
}
