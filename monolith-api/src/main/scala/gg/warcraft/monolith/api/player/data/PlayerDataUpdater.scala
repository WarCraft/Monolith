package gg.warcraft.monolith.api.player.data

import gg.warcraft.monolith.api.player.{Player, PlayerService}

class PlayerDataUpdater(implicit
    playerService: PlayerService,
    playerDataService: PlayerDataService
) extends Runnable {
  private var iterator: Iterator[Player] = Iterator.empty

  override def run(): Unit = {
    if (iterator.isEmpty) iterator = playerService.getOnlinePlayers.iterator
    if (iterator.hasNext) {
      val player = iterator.next
      if (player.isOnline) playerDataService.updatePlayerData(player.id)
    }
  }
}
