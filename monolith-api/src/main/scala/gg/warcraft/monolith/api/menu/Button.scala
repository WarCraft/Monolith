package gg.warcraft.monolith.api.menu

import gg.warcraft.monolith.api.core.ColorCode._
import gg.warcraft.monolith.api.item.{ItemType, ItemTypeOrVariant}
import gg.warcraft.monolith.api.player.Player

case class Button(
    icon: ItemTypeOrVariant,
    title: String,
    tooltip: List[String]
)(val action: Click => Unit) {
  lazy val formattedTitle: String = s"$WHITE$title"
  lazy val formattedTooltip: List[String] = tooltip.map { it => s"$GRAY$it" }
}

trait SkullButton {
  Button =>

  val playerName: String
  val icon: ItemTypeOrVariant
  val title: String
  val tooltip: List[String]
  val action: Click => Unit
}

object Button {
  def skull(
      name: String,
      title: String,
      tooltip: List[String]
  )(action: Click => Unit): Button =
    new Button(ItemType.MOB_HEAD, title, tooltip)(action) with SkullButton {
      override val playerName: String = name
    }

  def back(menu: Player => Menu)(implicit
      menuService: MenuService
  ): Button = {
    val tooltip = "[CLICK] To go back to" :: "the previous menu" :: Nil
    Button(ItemType.DOOR, "Back", tooltip) { click =>
      menuService.showMenu(click.player.id, menu(click.player))
    }
  }
}
