package gg.warcraft.monolith.api.world.block.box;

import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * BoundingBlockBox is a utility class that allows consumers to test whether a given {@code Block} lies within the
 * bounding box.
 */
public interface BoundingBlockBox extends Predicate<BlockLocation> {

    /**
     * @return The world this bounding box is in.
     */
    World getWorld();

    /**
     * @return The minimum corner of this bounding box.
     */
    BlockLocation getMinimumCorner();

    /**
     * @return The maximum corner of this bounding box.
     */
    BlockLocation getMaximumCorner();

    /**
     * @return The north boundary of this bounding box.
     */
    int getNorthBoundary();

    /**
     * @return The east boundary of this bounding box.
     */
    int getEastBoundary();

    /**
     * @return The south boundary of this bounding box.
     */
    int getSouthBoundary();

    /**
     * @return The west boundary of this bounding box.
     */
    int getWestBoundary();

    /**
     * @return The upper boundary of this bounding box.
     */
    int getUpperBoundary();

    /**
     * @return The lower boundary of this bounding box.
     */
    int getLowerBoundary();

    /**
     * @param type The block types.
     * @return All blocks of the given types within this bounding box.
     */
    List<Block> getBlocksOfType(BlockType... type);

    /**
     * @return A reader for this bounding box orientated towards the given direction.
     */
    BoundingBlockBoxReader getReader(Direction readDirection);

    /**
     * @param pivot   The block to rotate around. Can not be null.
     * @param degrees The degrees of the rotation. Can be any multiple of 90. Positive degrees rotate clockwise,
     *                negative degrees rotate counterclockwise.
     * @return A copy of this bounding box rotated around the Y axis at the pivot by the specified degrees.
     */
    BoundingBlockBox rotateY(BlockLocation pivot, int degrees);

    /**
     * @param vector The translation.
     * @return A copy of this bounding box translated by the specified vector.
     */
    BoundingBlockBox translate(Vector3i vector);

    /**
     * @return A lazy stream of all blocks within this bounding box.
     */
    Stream<Block> stream();

    /**
     * @param x The x coordinate.
     * @return A lazy stream of all blocks within this bounding box with coordinate x.
     */
    Stream<Block> sliceX(int x);

    /**
     * @param y The y coordinate.
     * @return A lazy stream of all blocks within this bounding boy with coordinate y.
     */
    Stream<Block> sliceY(int y);

    /**
     * @param z The z coordinate.
     * @return A lazy stream of all blocks within this bounding boz with coordinate z.
     */
    Stream<Block> sliceZ(int z);
}
