/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
