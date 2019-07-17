package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;

public interface Slab extends Block {

    /**
     * @return True if this slab is at the top of its block, false otherwise.
     */
    boolean isTop();

    /**
     * @return True if this slab is at the bottom of its block, false otherwise.
     */
    boolean isBottom();

    @Override
    Slab withLocation(BlockLocation location);
}
