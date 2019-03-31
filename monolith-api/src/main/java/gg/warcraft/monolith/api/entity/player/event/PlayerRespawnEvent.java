package gg.warcraft.monolith.api.entity.player.event;

import gg.warcraft.monolith.api.world.location.Location;

public interface PlayerRespawnEvent extends PlayerEvent {

    Location getLocation();
}
