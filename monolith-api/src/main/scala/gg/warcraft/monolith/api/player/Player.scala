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

import gg.warcraft.monolith.api.core.{GameMode, Message}
import gg.warcraft.monolith.api.core.auth.Principal
import gg.warcraft.monolith.api.entity.Entity
import gg.warcraft.monolith.api.item.Inventory
import gg.warcraft.monolith.api.player.currency.Currencies
import gg.warcraft.monolith.api.player.data.PlayerData
import gg.warcraft.monolith.api.player.statistic.Statistics
import gg.warcraft.monolith.api.util.typing._

object Player {
  type Mode = GameMode.Value
}

trait Player extends Entity with Principal {
  override def isPlayer: Boolean = true

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

  override def equals(other: Any): Boolean =
    other.is[Player] && other.as[Player].id == id

  override def hashCode(): Int = id.hashCode
}
