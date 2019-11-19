package gg.warcraft.monolith.api.world.item

import java.util.UUID

import gg.warcraft.monolith.api.core.{CancellableEvent, Event}
import gg.warcraft.monolith.api.entity.EntityType

trait ItemEvent

case class ItemPrePickupEvent(
    item: Item,
    itemId: UUID,
    entityId: UUID,
    entityType: EntityType,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends ItemEvent
    with CancellableEvent

case class ItemPickupEvent(
    item: Item,
    itemId: UUID,
    entityId: UUID,
    entityType: EntityType
) extends ItemEvent
    with Event
