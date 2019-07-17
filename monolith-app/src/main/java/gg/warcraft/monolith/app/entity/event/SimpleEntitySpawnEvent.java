package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityEvent;
import gg.warcraft.monolith.api.entity.event.EntitySpawnEvent;
import gg.warcraft.monolith.api.world.Location;

import java.util.UUID;

public class SimpleEntitySpawnEvent extends AbstractEntityEvent implements EntitySpawnEvent {
    private final Location location;

    public SimpleEntitySpawnEvent(UUID entityId, EntityType entityType, Location location) {
        super(entityId, entityType);
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}
