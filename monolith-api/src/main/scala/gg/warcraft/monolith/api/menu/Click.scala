package gg.warcraft.monolith.api.menu

import gg.warcraft.monolith.api.player.Player

case class Click(
    player: Player,
    isLeft: Boolean,
    isShift: Boolean
) {
  lazy val isRight: Boolean = !isLeft
  lazy val isShiftLeft: Boolean = isLeft && isShift
  lazy val isShiftRight: Boolean = isRight && isShift
}
