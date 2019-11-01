package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityEvent;
import gg.warcraft.monolith.api.entity.event.EntityInteractEvent;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public class SimpleEntityInteractEvent extends AbstractEntityEvent implements EntityInteractEvent {
    private final UUID playerId;
    private final Item itemInClickHand;
    private final Location interactLocation;

    public SimpleEntityInteractEvent(UUID entityId, EntityType entityType, UUID playerId, Item itemInClickHand,
                                     Location interactLocation) {
        super(entityId, entityType);
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
