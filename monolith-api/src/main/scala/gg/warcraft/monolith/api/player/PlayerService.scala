package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.entity.Equipment
import gg.warcraft.monolith.api.item.Item

trait PlayerService {
  def getPlayer(id: UUID): Option[Player]
  def getOfflinePlayer(id: UUID): OfflinePlayer
  def getOnlinePlayers: List[Player]
  def resolvePlayerId(name: String): UUID

  def setExperience(id: UUID, progress: Float): Unit
  def setLevel(id: UUID, level: Int): Unit
  def setEquipment(id: UUID, equipment: Equipment, force: Boolean): Boolean
  def setEquipment(
      id: UUID,
      slot: Equipment.Slot,
      item: Item,
      force: Boolean
  ): Boolean
  def giveItem(id: UUID, item: Item*): List[Item]

  def sendMessage(id: UUID, message: Message): Unit
  def sendTitle(id: UUID, title: String = "", subTitle: String = ""): Unit
}
