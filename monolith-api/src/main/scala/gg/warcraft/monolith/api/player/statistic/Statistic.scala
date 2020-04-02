package gg.warcraft.monolith.api.player.statistic

import java.util.UUID

private case class Statistic(
    playerId: UUID,
    statistic: String,
    value: Long = 0
) {
  def increase(amount: Long): Statistic = copy(value = value + amount)
}
