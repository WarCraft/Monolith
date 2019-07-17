package gg.warcraft.monolith.app.entity;

import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityIntersection;
import gg.warcraft.monolith.api.world.Location;

public class SimpleEntityIntersection implements EntityIntersection {
    private final Entity entity;
    private final Location location;

    public SimpleEntityIntersection(Entity entity, Location location) {
        this.entity = entity;
        this.location = location;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}
