package gg.warcraft.monolith.app.item.event;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.item.event.AbstractItemPreEvent;
import gg.warcraft.monolith.api.item.event.ItemPrePickupEvent;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public class SimpleItemPrePickupEvent extends AbstractItemPreEvent implements ItemPrePickupEvent {
    private final UUID entityId;
    private final EntityType entityType;

    public SimpleItemPrePickupEvent(UUID itemId, Item item, UUID entityId, EntityType entityType, boolean cancelled) {
        super(itemId, item, cancelled);
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
