package gg.warcraft.monolith.api.player.statistic.archive

import java.time.LocalDate
import java.util.UUID

case class StatisticArchive(
    playerId: UUID,
    statistic: String,
    value: Long = 0,
    date: LocalDate
)
