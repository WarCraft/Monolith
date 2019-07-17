package gg.warcraft.monolith.app.world.block.box;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.util.Offset;
import gg.warcraft.monolith.api.util.TriFunction;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBox;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBoxReader;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.app.util.SimpleOffset;

import java.util.function.Function;

/**
 * SimpleBoundingBlockBoxReader reads from the closest left corner depending on the read direction. This means that when
 * reading north the Z-axis is flipped as positive Z is closer than negative Z.
 */
public class SimpleBoundingBlockBoxReader implements BoundingBlockBoxReader {
    private final BoundingBlockBox boundingBlockBox;

    final TriFunction<Integer, Integer, Integer, Block> readBlockAt;
    final Function<Block, Offset> readOffsetFor;

    @Inject
    public SimpleBoundingBlockBoxReader(WorldQueryService worldQueryService, BoundingBlockBox boundingBlockBox,
                                        Direction readDirection) {
        this.boundingBlockBox = boundingBlockBox;

        World world = boundingBlockBox.getWorld();
        switch (readDirection) {
            case NORTH:
                readBlockAt = (horizontalOffset, verticalOffset, depthOffset) -> {
                    int x = boundingBlockBox.getWestBoundary() + horizontalOffset;
                    int y = boundingBlockBox.getLowerBoundary() + verticalOffset;
                    int z = boundingBlockBox.getSouthBoundary() - depthOffset;
                    return worldQueryService.getBlockAt(world, x, y, z);
                };
                readOffsetFor = (block) -> {
                    int horizontalOffset = block.getLocation().x() - boundingBlockBox.getWestBoundary();
                    int verticalOffset = block.getLocation().y() - boundingBlockBox.getLowerBoundary();
                    int depthOffset = boundingBlockBox.getSouthBoundary() - block.getLocation().z();
                    return new SimpleOffset(horizontalOffset, verticalOffset, depthOffset);
                };
                break;
            case EAST:
                readBlockAt = (horizontalOffset, verticalOffset, depthOffset) -> {
                    int x = boundingBlockBox.getWestBoundary() + depthOffset;
                    int y = boundingBlockBox.getLowerBoundary() + verticalOffset;
                    int z = boundingBlockBox.getNorthBoundary() + horizontalOffset;
                    return worldQueryService.getBlockAt(world, x, y, z);
                };
                readOffsetFor = (block) -> {
                    int horizontalOffset = block.getLocation().z() - boundingBlockBox.getNorthBoundary();
                    int verticalOffset = block.getLocation().y() - boundingBlockBox.getLowerBoundary();
                    int depthOffset = block.getLocation().x() - boundingBlockBox.getWestBoundary();
                    return new SimpleOffset(horizontalOffset, verticalOffset, depthOffset);
                };
                break;
            case SOUTH:
                readBlockAt = (horizontalOffset, verticalOffset, depthOffset) -> {
                    int x = boundingBlockBox.getEastBoundary() - horizontalOffset;
                    int y = boundingBlockBox.getLowerBoundary() + verticalOffset;
                    int z = boundingBlockBox.getNorthBoundary() + depthOffset;
                    return worldQueryService.getBlockAt(world, x, y, z);
                };
                readOffsetFor = (block) -> {
                    int horizontalOffset = boundingBlockBox.getEastBoundary() - block.getLocation().x();
                    int verticalOffset = block.getLocation().y() - boundingBlockBox.getLowerBoundary();
                    int depthOffset = block.getLocation().z() - boundingBlockBox.getNorthBoundary();
                    return new SimpleOffset(horizontalOffset, verticalOffset, depthOffset);
                };
                break;
            case WEST:
                readBlockAt = (horizontalOffset, verticalOffset, depthOffset) -> {
                    int x = boundingBlockBox.getEastBoundary() - depthOffset;
                    int y = boundingBlockBox.getLowerBoundary() + verticalOffset;
                    int z = boundingBlockBox.getSouthBoundary() - horizontalOffset;
                    return worldQueryService.getBlockAt(world, x, y, z);
                };
                readOffsetFor = (block) -> {
                    int horizontalOffset = boundingBlockBox.getSouthBoundary() - block.getLocation().z();
                    int verticalOffset = block.getLocation().y() - boundingBlockBox.getLowerBoundary();
                    int depthOffset = boundingBlockBox.getEastBoundary() - block.getLocation().x();
                    return new SimpleOffset(horizontalOffset, verticalOffset, depthOffset);
                };
                break;
            case UP:
            case DOWN:
            default:
                throw new IllegalArgumentException("Failed to initialize bounding block box reader with illegal read direction " + readDirection);
        }
    }

    @Override
    public Block getBlockAt(int horizontalOffset, int verticalOffset, int depthOffset) {
        return readBlockAt.apply(horizontalOffset, verticalOffset, depthOffset);
    }

    @Override
    public Block getBlockAt(Offset offset) {
        return getBlockAt(offset.getHorizontalOffset().intValue(),
                offset.getVerticalOffset().intValue(),
                offset.getDepthOffset().intValue());
    }

    @Override
    public Offset getOffsetFor(Block target) {
        return readOffsetFor.apply(target);
    }
}
