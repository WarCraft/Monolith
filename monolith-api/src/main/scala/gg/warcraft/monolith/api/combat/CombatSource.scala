package gg.warcraft.monolith.api.combat

import java.util.UUID

case class CombatSource(
    name: String,
    entityId: Option[UUID]
)
