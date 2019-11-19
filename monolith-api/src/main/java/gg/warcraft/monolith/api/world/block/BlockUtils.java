package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBox;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * This utility is injectable.
 * <p>
 * The BlockUtils utility services as a point of entry into the block implementation. It provides methods to easily
 * extract neighbouring blocks.
 */
public interface BlockUtils {

    /**
     * @param block The block. Can not be null.
     * @return All blocks adjacent to the block. Never null or empty.
     */
    Set<Block> getAdjacentBlocks(Block block);

    /**
     * @param block The block. Can not be null.
     * @param type  The type. Can not be null.
     * @return All blocks adjacent to the block of the specified type. Never null or empty.
     */
    Set<Block> getAdjacentBlocks(Block block, BlockType type);

    /**
     * @param block The block. Can not be null.
     * @return All blocks adjacent to the block along the X axis. Never null or empty.
     */
    Set<Block> getAdjacentBlocksX(Block block);

    /**
     * @param block The block. Can not be null.
     * @return All blocks adjacent to the block along the Y axis. Never null or empty.
     */
    Set<Block> getAdjacentBlocksY(Block block);

    /**
     * @param block The block. Can not be null.
     * @return All blocks adjacent to the block along the Z axis. Never null or empty.
     */
    Set<Block> getAdjacentBlocksZ(Block block);

    /**
     * @param block The block. Can not be null.
     * @return All blocks adjacent to the block along the X and Y axes. Never null or empty.
     */
    Set<Block> getAdjacentBlocksXY(Block block);

    /**
     * @param block The block. Can not be null.
     * @return All blocks adjacent to the block along the X and Z axes. Never null or empty.
     */
    Set<Block> getAdjacentBlocksXZ(Block block);

    /**
     * @param block The block. Can not be null.
     * @return All blocks adjacent to the block along the Y and Z axes. Never null or empty.
     */
    Set<Block> getAdjacentBlocksYZ(Block block);

    /**
     * @param block The block. Can not be null.
     * @param at    The block face. Can not be null.
     * @return The block neighbouring this block at the given block face. Never null.
     */
    Block getRelative(Block block, BlockFace at);

    /**
     * @param blocks The collection of blocks. Can not be null or empty.
     * @return A block box representing the bounds of the collection of blocks. Never null.
     */
    BoundingBlockBox getBoundingBox(Collection<Block> blocks);

    /**
     * @param location The location. Can not be null.
     * @param radius   The radius.
     * @return All blocks whose bounding boxes collide with the sphere created from the location and radius. Never null
     * or empty.
     */
    List<Block> getWithinRadius(Location location, float radius);

    /**
     * @param origin The starting location. Can not be null.
     * @param target The target location. Can not be null.
     * @param ignore The block types to ignore. Can not be null, but can be empty.
     * @return The closest block the ray starting at origin towards target intersected who's type is not in ignore.
     * Can be null.
     */
    BlockIntersection intersectBlock(Location origin, Location target, Predicate<Block> ignore);
}
