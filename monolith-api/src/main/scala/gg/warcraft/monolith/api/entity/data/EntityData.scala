package gg.warcraft.monolith.api.entity.data

import java.util.UUID

import gg.warcraft.monolith.api.entity.team.Team

case class EntityData(
    id: UUID,
    team: Option[Team] = None
)
