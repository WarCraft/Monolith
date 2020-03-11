package gg.warcraft.monolith.api.menu

import java.util.UUID

case class Click(
    playerId: UUID,
    leftClick: Boolean,
    shiftClick: Boolean
) {
  lazy val rightClick: Boolean = !leftClick
  lazy val shiftLeftClick: Boolean = leftClick && shiftClick
  lazy val shiftRightClick: Boolean = rightClick && shiftClick
}
