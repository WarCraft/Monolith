package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;

public interface DirectionalBlock extends Block {

    /**
     * @return The direction this block is facing. Never null.
     */
    Direction getFacing();

    /**
     * @param facing The new facing direction. Can not be null.
     * @return A copy of this block with the new facing direction. Never null.
     */
    DirectionalBlock withFacing(Direction facing);

    @Override
    DirectionalBlock withLocation(BlockLocation location);
}
