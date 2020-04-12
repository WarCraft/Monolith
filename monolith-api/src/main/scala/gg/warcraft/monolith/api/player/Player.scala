package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.core.{GameMode, Message}
import gg.warcraft.monolith.api.core.auth.Principal
import gg.warcraft.monolith.api.entity.Entity
import gg.warcraft.monolith.api.item.Inventory
import gg.warcraft.monolith.api.player.currency.Currencies
import gg.warcraft.monolith.api.player.data.PlayerData
import gg.warcraft.monolith.api.player.statistic.Statistics

object Player {
  type Mode = GameMode.Value
}

trait Player extends Entity with Principal {
  val typed: Entity.Type = Entity.Type.PLAYER
  val principalId: Option[UUID] = Some(id)

  def data: PlayerData
  def currencies: Currencies
  def statistics: Statistics

  def inventory: Inventory
  def mode: Player.Mode
  def isAlive: Boolean
  def isOnline: Boolean
  def isSneaking: Boolean

  def sendMessage(message: Message): Unit
  def sendTitle(title: String = "", subTitle: String = ""): Unit
}
