package gg.warcraft.monolith.api.player.data

import java.util.UUID

import gg.warcraft.monolith.api.entity.team.Team

case class PlayerData(
    id: UUID,
    team: Option[Team] = None,
    timeConnected: Long = 0,
    timeLastSeen: Long = 0
)
