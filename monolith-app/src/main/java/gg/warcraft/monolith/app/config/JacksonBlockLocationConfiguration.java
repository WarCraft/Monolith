package gg.warcraft.monolith.app.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gg.warcraft.monolith.api.config.BlockLocationConfiguration;
import gg.warcraft.monolith.api.world.World;

public class JacksonBlockLocationConfiguration implements BlockLocationConfiguration {
    private final World world;
    private final int x;
    private final int y;
    private final int z;

    @JsonCreator
    public JacksonBlockLocationConfiguration(@JsonProperty("world") World world,
                                             @JsonProperty("x") int x,
                                             @JsonProperty("y") int y,
                                             @JsonProperty("z") int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }
}
