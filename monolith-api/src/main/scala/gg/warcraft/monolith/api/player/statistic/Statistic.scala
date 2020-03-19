package gg.warcraft.monolith.api.player.statistic

import java.util.UUID

private case class Statistic(
    playerId: UUID,
    statistic: String,
    value: Int = 0
) {
  def increase(amount: Int): Statistic = copy(value = value + amount)
}
