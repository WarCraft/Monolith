package gg.warcraft.monolith.api.block.spoofing

import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.player.PlayerDisconnectEvent

class BlockSpoofingHandler(service: BlockSpoofingService) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case it: PlayerDisconnectEvent => service unspoofAll it.playerId
    case _                         =>
  }
}
