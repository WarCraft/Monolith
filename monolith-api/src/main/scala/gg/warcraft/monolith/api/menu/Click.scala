package gg.warcraft.monolith.api.menu

import java.util.UUID

case class Click(
    playerId: UUID,
    isLeft: Boolean,
    isShift: Boolean
) {
  lazy val isRight: Boolean = !isLeft
  lazy val isShiftLeft: Boolean = isLeft && isShift
  lazy val isShiftRight: Boolean = isRight && isShift
}
