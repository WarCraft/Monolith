package gg.warcraft.monolith.app.world.block.box;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBox;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBoxReader;
import gg.warcraft.monolith.api.world.location.BlockLocation;
import gg.warcraft.monolith.api.world.location.LocationFactory;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleBoundingBlockBox implements BoundingBlockBox {
    private final WorldQueryService worldQueryService;
    private final LocationFactory locationFactory;
    private final World world;
    private final BlockLocation minimumCorner;
    private final BlockLocation maximumCorner;
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final int minZ;
    private final int maxZ;

    @Inject
    public SimpleBoundingBlockBox(WorldQueryService worldQueryService, LocationFactory locationFactory,
                                  @Assisted World world, @Assisted("minimum") Vector3ic minimumCorner,
                                  @Assisted("maximum") Vector3ic maximumCorner) {
        this.worldQueryService = worldQueryService;
        this.locationFactory = locationFactory;
        this.world = worldQueryService.getWorld(world);
        this.minimumCorner = locationFactory.createBlockLocation(world, minimumCorner.x(), minimumCorner.y(),
                minimumCorner.z());
        this.maximumCorner = locationFactory.createBlockLocation(world, maximumCorner.x(), maximumCorner.y(),
                maximumCorner.z());
        this.minX = Math.min(minimumCorner.x(), maximumCorner.x());
        this.maxX = Math.max(minimumCorner.x(), maximumCorner.x());
        this.minY = Math.min(minimumCorner.y(), maximumCorner.y());
        this.maxY = Math.max(minimumCorner.y(), maximumCorner.y());
        this.minZ = Math.min(minimumCorner.z(), maximumCorner.z());
        this.maxZ = Math.max(minimumCorner.z(), maximumCorner.z());
    }

    @Override
    public boolean test(BlockLocation location) {
        if (location.getWorld() != world) {
            return false;
        }

        int x = location.getX();
        int y = location.getY();
        int z = location.getZ();
        return x >= minX && x <= maxX &&
                y >= minY && y <= maxY &&
                z >= minZ && z <= maxZ;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public BlockLocation getMinimumCorner() {
        return minimumCorner;
    }

    @Override
    public BlockLocation getMaximumCorner() {
        return maximumCorner;
    }

    @Override
    public int getNorthBoundary() {
        return minZ;
    }

    @Override
    public int getEastBoundary() {
        return maxX;
    }

    @Override
    public int getSouthBoundary() {
        return maxZ;
    }

    @Override
    public int getWestBoundary() {
        return minX;
    }

    @Override
    public int getUpperBoundary() {
        return maxY;
    }

    @Override
    public int getLowerBoundary() {
        return minY;
    }

    @Override
    public List<Block> getBlocksOfType(BlockType... type) {
        Set<BlockType> types = new HashSet<>(Arrays.asList(type));
        return stream()
                .filter(block -> types.contains(block.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public BoundingBlockBoxReader getReader(Direction readDirection) {
        return new SimpleBoundingBlockBoxReader(worldQueryService, this, readDirection);
    }

    @Override
    public BoundingBlockBox rotateY(BlockLocation pivot, int degrees) {
        int pivotX = pivot.getX();
        int pivotZ = pivot.getZ();
        int deltaNorthBoundary = pivotZ - getNorthBoundary();
        int deltaEastBoundary = getEastBoundary() - pivotX;
        int deltaSouthBoundary = getSouthBoundary() - pivotZ;
        int deltaWestBoundary = pivotX - getWestBoundary();

        // translate negative rotation to positive rotation
        while (degrees < 0) {
            degrees += 360;
        }

        // translate large rotation to predictable rotation
        degrees %= 360;

        if (degrees % 90 != 0) {
            throw new IllegalArgumentException("Failed to rotate bounding block box for illegal rotation of "
                    + degrees + ", rotation must be a multiple of 90.");
        }

        switch (degrees / 90) {
            case 0:
                return this;
            case 1:
                Vector3i newMinimumCorner90 = new Vector3i(
                        pivotX - deltaSouthBoundary,
                        getLowerBoundary(),
                        pivotZ - deltaWestBoundary);
                Vector3i newMaximumCorner90 = new Vector3i(
                        pivotX + deltaNorthBoundary,
                        getUpperBoundary(),
                        pivotZ + deltaEastBoundary);
                return new SimpleBoundingBlockBox(worldQueryService, locationFactory, world,
                        newMinimumCorner90, newMaximumCorner90);
            case 2:
                Vector3i newMinimumCorner180 = new Vector3i(
                        pivotX - deltaEastBoundary,
                        getLowerBoundary(),
                        pivotZ - deltaSouthBoundary);
                Vector3i newMaximumCorner180 = new Vector3i(
                        pivotX + deltaWestBoundary,
                        getUpperBoundary(),
                        pivotZ + deltaNorthBoundary);
                return new SimpleBoundingBlockBox(worldQueryService, locationFactory, world,
                        newMinimumCorner180, newMaximumCorner180);
            case 3:
                Vector3i newMinimumCorner270 = new Vector3i(
                        pivotX - deltaNorthBoundary,
                        getLowerBoundary(),
                        pivotZ - deltaEastBoundary);
                Vector3i newMaximumCorner270 = new Vector3i(
                        pivotX + deltaSouthBoundary,
                        getUpperBoundary(),
                        pivotZ + deltaWestBoundary);
                return new SimpleBoundingBlockBox(worldQueryService, locationFactory, world,
                        newMinimumCorner270, newMaximumCorner270);
            default:
                throw new IllegalArgumentException("Failed to rotate bounding block box for illegal rotation of " + degrees);
        }
    }

    @Override
    public BoundingBlockBox translate(Vector3ic vector) {
        Vector3i newMinimumCorner = new Vector3i(minimumCorner.toVector()).add(vector);
        Vector3i newMaximumCorner = new Vector3i(maximumCorner.toVector()).add(vector);
        return new SimpleBoundingBlockBox(worldQueryService, locationFactory, world,
                newMinimumCorner, newMaximumCorner);
    }

    @Override
    public Stream<Block> stream() {
        return Stream
                .iterate(minX, currentX -> currentX + 1)
                .limit(maxX - minX + 1)
                .flatMap(x -> Stream
                        .iterate(minY, currentY -> currentY + 1)
                        .limit(maxY - minY + 1)
                        .flatMap(y -> Stream
                                .iterate(minZ, currentZ -> currentZ + 1)
                                .limit(maxZ - minZ + 1)
                                .map(z -> worldQueryService.getBlockAt(getWorld(), x, y, z))));
    }

    @Override
    public Stream<Block> sliceX(int x) {
        return Stream
                .iterate(minY, currentY -> currentY + 1)
                .limit(maxY - minY + 1)
                .flatMap(y -> Stream
                        .iterate(minZ, currentZ -> currentZ + 1)
                        .limit(maxZ - minZ + 1)
                        .map(z -> worldQueryService.getBlockAt(getWorld(), x, y, z)));
    }

    @Override
    public Stream<Block> sliceY(int y) {
        return Stream
                .iterate(minX, currentX -> currentX + 1)
                .limit(maxX - minX + 1)
                .flatMap(x -> Stream
                        .iterate(minZ, currentZ -> currentZ + 1)
                        .limit(maxZ - minZ + 1)
                        .map(z -> worldQueryService.getBlockAt(getWorld(), x, y, z)));
    }

    @Override
    public Stream<Block> sliceZ(int z) {
        return Stream
                .iterate(minX, currentX -> currentX + 1)
                .limit(maxX - minX + 1)
                .flatMap(x -> Stream
                        .iterate(minY, currentY -> currentY + 1)
                        .limit(maxY - minY + 1)
                        .map(y -> worldQueryService.getBlockAt(getWorld(), x, y, z)));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("world", getWorld())
                .add("minX", getWestBoundary())
                .add("minY", getLowerBoundary())
                .add("minZ", getNorthBoundary())
                .add("maxX", getEastBoundary())
                .add("maxY", getUpperBoundary())
                .add("maxZ", getSouthBoundary())
                .toString();
    }
}
