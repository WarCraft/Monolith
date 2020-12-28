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

package gg.warcraft.monolith.api.menu

import gg.warcraft.monolith.api.core.ColorCode._
import gg.warcraft.monolith.api.core.FormatCode._
import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.item.{ItemType, ItemTypeOrVariant}
import gg.warcraft.monolith.api.player.Player

case class Button(
    icon: ItemTypeOrVariant,
    title: String,
    tooltip: List[String]
)(val action: Click => Button.Result) {
  lazy val formattedTitle: String = s"$RESET$WHITE$title"
  lazy val formattedTooltip: List[String] = tooltip.map { it => s"$RESET$GRAY$it" }
}

trait SkullButton extends Button {
  val playerName: String
}

object Button {
  sealed trait Result // TODO make union with Unit in Scala 3

  case object noop extends Result
  case object closeMenu extends Result
  final case class closeMenu(message: Message) extends Result
  final case class updateMenu(menu: Player => Menu) extends Result
  final case class updateButton(button: Button) extends Result

  def apply(
      icon: ItemTypeOrVariant, // TODO make union with String in Scala 3
      title: String,
      tooltip: String*
  )(action: Click => Result): Button =
    Button(icon, title, tooltip.toList)(action)

  def noop(
      icon: ItemTypeOrVariant,
      title: String,
      tooltip: String*
  ): Button = Button(icon, title, tooltip.toList) { _ => noop }

  def skull(
      name: String,
      title: String,
      tooltip: String*
  )(action: Click => Result): Button =
    new Button(ItemType.MOB_HEAD, title, tooltip.toList)(action) with SkullButton {
      override val playerName: String = name
    }

  def back(menu: Player => Menu)(implicit
      menuService: MenuService
  ): Button = {
    val tooltip = "[CLICK] To go back to" :: "the previous menu" :: Nil
    Button(ItemType.DOOR, "Back", tooltip) { _ => updateMenu(menu) }
  }
}
