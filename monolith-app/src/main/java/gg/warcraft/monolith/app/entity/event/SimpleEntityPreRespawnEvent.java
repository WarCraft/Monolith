package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityPreEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreRespawnEvent;
import gg.warcraft.monolith.api.world.Location;

import java.util.UUID;

public class SimpleEntityPreRespawnEvent extends AbstractEntityPreEvent implements EntityPreRespawnEvent {
    private Location location;

    public SimpleEntityPreRespawnEvent(UUID entityId, EntityType entityType, Location location, boolean cancelled) {
        super(entityId, entityType, cancelled);
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
