package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;

/**
 * A Block represents a block as found on the server.
 */
public interface Block {

    /**
     * @return The type of this block. Never null.
     */
    BlockType getType();

    /**
     * @return The location of this block. Never null.
     */
    BlockLocation getLocation();

    /**
     * @param face The block face.
     * @return The block neighbouring this block at the given block face. Never null.
     */
    Block getRelative(BlockFace face);
}
