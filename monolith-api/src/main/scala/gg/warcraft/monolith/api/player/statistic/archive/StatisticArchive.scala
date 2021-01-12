package gg.warcraft.monolith.api.player.statistic.archive

import gg.warcraft.monolith.api.util.types.MonolithDate

import java.util.UUID

case class StatisticArchive(
    playerId: UUID,
    statistic: String,
    value: Long = 0,
    date: MonolithDate
)
