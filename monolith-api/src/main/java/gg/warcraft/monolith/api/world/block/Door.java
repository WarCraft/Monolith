package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;

public interface Door extends DirectionalBlock {

    Hinge getHinge();

    boolean isOpen();

    boolean isTopHalf();

    Door withHinge(Hinge hinge);

    Door withOpen(boolean open);

    @Override
    Door withFacing(Direction facing);

    @Override
    Door withLocation(BlockLocation location);
}
