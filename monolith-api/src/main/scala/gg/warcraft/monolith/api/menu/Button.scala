package gg.warcraft.monolith.api.menu

import java.util.UUID

import gg.warcraft.monolith.api.core.ColorCode._
import gg.warcraft.monolith.api.item.{ItemType, ItemTypeOrVariant}

sealed trait SkullButton {
  this: Button =>
  val name: String
  val title: String
  val action: Click => Unit
  val tooltip: List[String]
}

object Button {
  def apply(
      icon: ItemTypeOrVariant,
      title: String,
      action: Click => Unit,
      tooltip: List[String] = Nil
  ): Button = {
    val formattedTitle = s"$WHITE$title"
    val formattedTooltip = tooltip map (it => s"$GRAY$it")
    new Button(icon, formattedTitle, action, formattedTooltip)
  }

  def skull(
      playerName: String,
      title: String,
      action: Click => Unit,
      tooltip: List[String] = Nil
  ): Button =
    new Button(ItemType.MOB_HEAD, title, action, tooltip) with SkullButton {
      override val name: String = playerName
    }

  def back(menu: UUID => Menu)(
      implicit menuService: MenuService
  ): Button = {
    val action = (it: Click) => menuService.showMenu(it.playerId, menu(it.playerId))
    val tooltip = "[CLICK] To go back to" :: "the previous menu" :: Nil
    apply(ItemType.DOOR, "Back", action, tooltip)
  }
}

case class Button private (
    icon: ItemTypeOrVariant,
    title: String,
    action: Click => Unit,
    tooltip: List[String]
)
