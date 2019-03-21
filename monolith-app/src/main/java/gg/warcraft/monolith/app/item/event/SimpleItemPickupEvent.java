package gg.warcraft.monolith.app.item.event;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.item.event.AbstractItemEvent;
import gg.warcraft.monolith.api.item.event.ItemPickupEvent;

import java.util.UUID;

public class SimpleItemPickupEvent extends AbstractItemEvent implements ItemPickupEvent {
    private final UUID entityId;
    private final EntityType entityType;

    public SimpleItemPickupEvent(UUID itemId, Item item, UUID entityId, EntityType entityType) {
        super(itemId, item);
        this.entityId = entityId;
        this.entityType = entityType;
    }

    @Override
    public UUID getEntityId() {
        return entityId;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }
}
