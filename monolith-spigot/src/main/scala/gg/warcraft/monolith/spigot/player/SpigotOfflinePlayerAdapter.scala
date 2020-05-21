package gg.warcraft.monolith.spigot.player

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.player.OfflinePlayer
import gg.warcraft.monolith.api.player.currency.{Currencies, CurrencyService}
import gg.warcraft.monolith.api.player.data.{PlayerData, PlayerDataService}
import gg.warcraft.monolith.api.player.statistic.{Statistics, StatisticService}

import scala.concurrent.{ExecutionContext, Future}

class SpigotOfflinePlayerAdapter(player: OfflineSpigotPlayer)(implicit
    logger: Logger,
    dataService: PlayerDataService,
    currencyService: CurrencyService,
    statisticService: StatisticService
) extends OfflinePlayer {
  private final implicit val context: ExecutionContext = ExecutionContext.global

  override lazy val id: UUID = player.getUniqueId
  override def name: String = player.getName
  override def isOnline: Boolean = false

  override def data: Future[PlayerData] =
    dataService getPlayerData player.getUniqueId
  override def currencies: Future[Currencies] =
    currencyService.currenciesFuture(player.getUniqueId)
  override def statistics: Future[Statistics] =
    statisticService.statisticsFuture(player.getUniqueId)
}
