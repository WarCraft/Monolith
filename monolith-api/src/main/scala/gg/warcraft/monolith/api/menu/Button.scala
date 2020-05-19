package gg.warcraft.monolith.api.menu

import java.util.UUID

import gg.warcraft.monolith.api.core.ColorCode._
import gg.warcraft.monolith.api.item.{ItemType, ItemTypeOrVariant}

case class Button(
    icon: ItemTypeOrVariant,
    title: String,
    tooltip: List[String]
)(action: Click => Unit = _ => {}) {
  lazy val formattedTitle: String = s"$WHITE$title"
  lazy val formattedTooltip: List[String] = tooltip.map { it => s"$GRAY$it" }
}

trait SkullButton {
  val playerName: String
}

object Button {
  def skull(
      name: String,
      title: String,
      tooltip: List[String]
  )(action: Click => Unit = _ => {}): Button =
    new Button(ItemType.MOB_HEAD, title, tooltip)(action) with SkullButton {
      override val playerName: String = name
    }

  def back(menu: UUID => Menu)(implicit
      menuService: MenuService
  ): Button = {
    val tooltip = "[CLICK] To go back to" :: "the previous menu" :: Nil
    Button(ItemType.DOOR, "Back", tooltip) { click =>
      menuService.showMenu(click.playerId, menu(click.playerId))
    }
  }
}
