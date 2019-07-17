package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.world.Location;

public interface EntityPreSpawnEvent extends EntityPreEvent {

    Location getLocation();

    void setLocation(Location location);
}
