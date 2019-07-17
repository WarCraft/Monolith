package gg.warcraft.monolith.app.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gg.warcraft.monolith.api.config.BoundingBlockBoxConfiguration;
import gg.warcraft.monolith.api.math.config.Vector3iConfig;
import gg.warcraft.monolith.api.world.World;

public class JacksonBoundingBlockBoxConfiguration implements BoundingBlockBoxConfiguration {
    private final World world;
    private final Vector3iConfig minimumCorner;
    private final Vector3iConfig maximumCorner;

    @JsonCreator
    public JacksonBoundingBlockBoxConfiguration(@JsonProperty("world") World world,
                                                @JsonProperty("minimumCorner") Vector3iConfig minimumCorner,
                                                @JsonProperty("maximumCorner") Vector3iConfig maximumCorner) {
        this.world = world;
        this.minimumCorner = minimumCorner;
        this.maximumCorner = maximumCorner;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Vector3iConfig getMinimumcorner() {
        return minimumCorner;
    }

    @Override
    public Vector3iConfig getMaximumcorner() {
        return maximumCorner;
    }
}
