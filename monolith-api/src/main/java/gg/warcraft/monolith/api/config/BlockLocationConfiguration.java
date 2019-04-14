package gg.warcraft.monolith.api.config;

import gg.warcraft.monolith.api.world.World;

public interface BlockLocationConfiguration {

    World getWorld();

    int getX();

    int getY();

    int getZ();
}
