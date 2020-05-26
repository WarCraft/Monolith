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

package gg.warcraft.monolith.api.entity

import gg.warcraft.monolith.api.item.Item

case class Equipment(
    head: Option[Item] = None,
    chest: Option[Item] = None,
    legs: Option[Item] = None,
    feet: Option[Item] = None,
    mainHand: Option[Item] = None,
    offHand: Option[Item] = None
) {
  lazy val items: List[Item] =
    (head :: chest :: legs :: feet :: mainHand :: offHand :: Nil)
      .filter { _.isDefined }
      .map { _.get }

  def get(slot: Equipment.Slot): Option[Item] = slot match {
    case Equipment.HEAD      => head
    case Equipment.CHEST     => chest
    case Equipment.LEGS      => legs
    case Equipment.FEET      => feet
    case Equipment.MAIN_HAND => mainHand
    case Equipment.OFF_HAND  => offHand
  }
}

object Equipment extends Enumeration {
  type Slot = Value
  val HEAD, CHEST, LEGS, FEET, MAIN_HAND, OFF_HAND = Value
}
