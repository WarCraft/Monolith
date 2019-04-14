package gg.warcraft.monolith.api.config;

import gg.warcraft.monolith.api.world.World;

public interface LocationConfiguration {

    World getWorld();

    float getX();

    float getY();

    float getZ();
}
