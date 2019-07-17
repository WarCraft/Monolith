package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.world.Location;

public interface EntityRespawnEvent extends EntityEvent {

    Location getLocation();
}
