package gg.warcraft.monolith.api.item.storage

import java.util.UUID

case class StoredItem(
    id: UUID,
    playerId: UUID,
    data: String
)
