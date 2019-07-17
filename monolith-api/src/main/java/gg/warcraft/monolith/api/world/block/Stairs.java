package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;

public interface Stairs extends DirectionalBlock {

    /**
     * @return True if this stairs block is inverted, false otherwise.
     */
    boolean isInverted();

    /**
     * @param inverted The new inversion.
     * @return A copy of this block with the given inversion. Never null.
     */
    Stairs withInverted(boolean inverted);

    @Override
    Stairs withLocation(BlockLocation location);

    @Override
    Stairs withFacing(Direction facing);
}
