package gg.warcraft.monolith.api.item.storage

import java.util.UUID

case class StoredItem(
    id: UUID,
    data: String,
    clazz: String
)
