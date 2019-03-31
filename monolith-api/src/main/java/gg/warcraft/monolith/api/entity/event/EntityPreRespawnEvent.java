package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.world.location.Location;

public interface EntityPreRespawnEvent extends EntityPreEvent {

    Location getLocation();

    void setLocation(Location location);
}
