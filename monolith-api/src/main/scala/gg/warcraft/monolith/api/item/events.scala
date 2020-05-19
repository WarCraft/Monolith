package gg.warcraft.monolith.api.item

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}
import gg.warcraft.monolith.api.entity.Entity

trait ItemEvent

case class ItemPrePickupEvent(
    item: Item,
    itemId: UUID,
    entityId: UUID,
    entityType: Entity.Type,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with ItemEvent

case class ItemPickupEvent(
    item: Item,
    itemId: UUID,
    entityId: UUID,
    entityType: Entity.Type
) extends Event
    with ItemEvent
