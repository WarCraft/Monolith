package gg.warcraft.monolith.api.config;

import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.location.config.LocationConfig;

public interface PortalConfiguration {

    LocationConfig getEntryLocation();

    LocationConfig getExitLocation();

    Direction getExitOrientation();
}
