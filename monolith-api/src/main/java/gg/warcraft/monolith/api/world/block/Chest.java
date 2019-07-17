package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;

public interface Chest extends DirectionalBlock {

    @Override
    Chest withLocation(BlockLocation location);

    @Override
    Chest withFacing(Direction facing);
}
