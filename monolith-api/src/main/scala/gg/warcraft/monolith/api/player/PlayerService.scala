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

package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.entity.Equipment
import gg.warcraft.monolith.api.item.Item

trait PlayerService {
  def getPlayer(id: UUID): Player
  def getPlayerOption(id: UUID): Option[Player]
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
