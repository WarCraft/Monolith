package gg.warcraft.monolith.api.menu

case class Menu(
    title: String,
    size: MenuSize,
    buttons: Map[Int, Button]
)
