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
     * Returns a copy of this block with a new type.
     * <p>
     * Care should be taken that this method only returns simple blocks even if a more specific implementation is
     * available (such as updating from grass to stairs).
     *
     * @param type The new type. Can not be null.
     * @return A copy of this block with the new type. Never null.
     */
    Block withType(BlockType type);

    /**
     * @param location The new location. Can not be null.
     * @return A copy of this block at the given location. Never null.
     */
    Block withLocation(BlockLocation location);
}
