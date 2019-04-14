package gg.warcraft.monolith.api.config;

import gg.warcraft.monolith.api.world.World;

public interface BoundingBlockBoxConfiguration {

    World getWorld();

    Vector3iConfiguration getMinimumcorner();

    Vector3iConfiguration getMaximumcorner();
}
