package gg.warcraft.monolith.app.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gg.warcraft.monolith.api.config.BoundingBlockBoxConfiguration;
import gg.warcraft.monolith.api.config.Vector3iConfiguration;
import gg.warcraft.monolith.api.world.World;

public class JacksonBoundingBlockBoxConfiguration implements BoundingBlockBoxConfiguration {
    private final World world;
    private final Vector3iConfiguration minimumCorner;
    private final Vector3iConfiguration maximumCorner;

    @JsonCreator
    public JacksonBoundingBlockBoxConfiguration(@JsonProperty("world") World world,
                                                @JsonProperty("minimumCorner") Vector3iConfiguration minimumCorner,
                                                @JsonProperty("maximumCorner") Vector3iConfiguration maximumCorner) {
        this.world = world;
        this.minimumCorner = minimumCorner;
        this.maximumCorner = maximumCorner;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Vector3iConfiguration getMinimumcorner() {
        return minimumCorner;
    }

    @Override
    public Vector3iConfiguration getMaximumcorner() {
        return maximumCorner;
    }
}
