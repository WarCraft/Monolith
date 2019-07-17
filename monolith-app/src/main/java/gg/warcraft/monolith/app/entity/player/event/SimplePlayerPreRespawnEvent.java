package gg.warcraft.monolith.app.entity.player.event;

import gg.warcraft.monolith.api.entity.player.event.AbstractPlayerEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerPreRespawnEvent;
import gg.warcraft.monolith.api.world.Location;

import java.util.UUID;

public class SimplePlayerPreRespawnEvent extends AbstractPlayerEvent implements PlayerPreRespawnEvent {
    private Location location;

    public SimplePlayerPreRespawnEvent(UUID playerId, Location location) {
        super(playerId);
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
