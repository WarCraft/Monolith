package gg.warcraft.monolith.api.config;

import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.config.LocationConfig;

public interface PortalConfiguration {

    LocationConfig getEntryLocation();

    LocationConfig getExitLocation();

    Direction getExitOrientation();
}
