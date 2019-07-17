package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityPreEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreInteractEvent;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.world.Location;

import java.util.UUID;

public class SimpleEntityPreInteractEvent extends AbstractEntityPreEvent implements EntityPreInteractEvent {
    private final UUID playerId;
    private final Item itemInClickHand;
    private final Location interactLocation;

    public SimpleEntityPreInteractEvent(UUID entityId, EntityType entityType, UUID playerId, Item itemInClickHand,
                                        Location interactLocation, boolean cancelled) {
        super(entityId, entityType, cancelled);
        this.playerId = playerId;
        this.itemInClickHand = itemInClickHand;
        this.interactLocation = interactLocation;
    }

    @Override
    public UUID getPlayerId() {
        return playerId;
    }

    @Override
    public Item getItemInHand() {
        return itemInClickHand;
    }

    @Override
    public Location getInteractLocation() {
        return interactLocation;
    }
}
