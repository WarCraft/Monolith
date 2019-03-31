package gg.warcraft.monolith.api.entity.player.event;

import gg.warcraft.monolith.api.world.location.Location;

public interface PlayerPreRespawnEvent extends PlayerEvent {

    Location getLocation();

    void setLocation(Location location);
}
