package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;

public interface Lever extends DirectionalBlock {

    boolean isPowered();

    Lever withPowered(boolean powered);

    @Override
    Lever withLocation(BlockLocation location);

    @Override
    Lever withFacing(Direction facing);
}
