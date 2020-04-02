package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.player.currency.Currencies
import gg.warcraft.monolith.api.player.data.PlayerData
import gg.warcraft.monolith.api.player.statistic.Statistics

import scala.concurrent.Future

trait OfflinePlayer {
  val id: UUID
  def name: String
  def isOnline: Boolean

  def data: Future[PlayerData]
  def currencies: Future[Currencies]
  def statistics: Future[Statistics]
}
