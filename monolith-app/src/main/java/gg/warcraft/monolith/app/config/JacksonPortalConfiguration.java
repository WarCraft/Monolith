package gg.warcraft.monolith.app.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gg.warcraft.monolith.api.config.PortalConfiguration;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.location.config.LocationConfig;

public class JacksonPortalConfiguration implements PortalConfiguration {
    private final LocationConfig entryLocation;
    private final LocationConfig exitLocation;
    private final Direction exitOrientation;

    @JsonCreator
    public JacksonPortalConfiguration(@JsonProperty("entryLocation") LocationConfig entryLocation,
                                      @JsonProperty("exitLocation") LocationConfig exitLocation,
                                      @JsonProperty("exitOrientation") Direction exitOrientation) {
        this.entryLocation = entryLocation;
        this.exitLocation = exitLocation;
        this.exitOrientation = exitOrientation;
    }

    @Override
    public LocationConfig getEntryLocation() {
        return entryLocation;
    }

    @Override
    public LocationConfig getExitLocation() {
        return exitLocation;
    }

    @Override
    public Direction getExitOrientation() {
        return exitOrientation;
    }
}
