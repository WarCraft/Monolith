package gg.warcraft.monolith.api.config;

import gg.warcraft.monolith.api.math.config.Vector3iConfig;
import gg.warcraft.monolith.api.world.World;

public interface BoundingBlockBoxConfiguration {

    World getWorld();

    Vector3iConfig getMinimumcorner();

    Vector3iConfig getMaximumcorner();
}
