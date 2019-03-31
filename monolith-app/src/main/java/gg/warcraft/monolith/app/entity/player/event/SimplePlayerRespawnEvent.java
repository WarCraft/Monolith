package gg.warcraft.monolith.app.entity.player.event;

import gg.warcraft.monolith.api.entity.player.event.AbstractPlayerEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerRespawnEvent;
import gg.warcraft.monolith.api.world.location.Location;

import java.util.UUID;

public class SimplePlayerRespawnEvent extends AbstractPlayerEvent implements PlayerRespawnEvent {
    private final Location location;

    public SimplePlayerRespawnEvent(UUID playerId, Location location) {
        super(playerId);
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}
