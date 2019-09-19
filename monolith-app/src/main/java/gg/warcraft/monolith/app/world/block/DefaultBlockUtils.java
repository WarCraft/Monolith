package gg.warcraft.monolith.app.world.block;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.*;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBox;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBoxFactory;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import org.joml.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultBlockUtils implements BlockUtils {
    private final WorldQueryService worldQueryService;
    private final BlockIteratorFactory blockIteratorFactory;
    private final BoundingBlockBoxFactory boundingBlockBoxFactory;

    @Inject
    public DefaultBlockUtils(WorldQueryService worldQueryService, BlockIteratorFactory blockIteratorFactory,
                             BoundingBlockBoxFactory boundingBlockBoxFactory) {
        this.worldQueryService = worldQueryService;
        this.blockIteratorFactory = blockIteratorFactory;
        this.boundingBlockBoxFactory = boundingBlockBoxFactory;
    }

    @Override
    public Set<Block> getAdjacentBlocks(Block block) {
        Set<Block> adjacentBlocks = new HashSet<>();
        adjacentBlocks.addAll(getAdjacentBlocksX(block));
        adjacentBlocks.addAll(getAdjacentBlocksY(block));
        adjacentBlocks.addAll(getAdjacentBlocksZ(block));
        return adjacentBlocks;
    }

    @Override
    public Set<Block> getAdjacentBlocks(Block block, BlockType type) {
        return getAdjacentBlocks(block).stream()
                .filter(adjacentBlock -> adjacentBlock.type() == type)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Block> getAdjacentBlocksX(Block block) {
        Set<Block> blocks = new HashSet<>();
        BlockLocation location = block.location();
        int currentX = location.x();

        BlockLocation leftNeighbourLocation = location.withX(currentX - 1);
        Block leftNeighbour = worldQueryService.getBlockAt(leftNeighbourLocation);
        blocks.add(leftNeighbour);

        BlockLocation rightNeighbourLocation = location.withX(currentX + 1);
        Block rightNeighbour = worldQueryService.getBlockAt(rightNeighbourLocation);
        blocks.add(rightNeighbour);

        return blocks;
    }

    @Override
    public Set<Block> getAdjacentBlocksY(Block block) {
        Set<Block> blocks = new HashSet<>();
        BlockLocation location = block.location();
        int currentY = location.y();

        BlockLocation leftNeighbourLocation = location.withY(currentY - 1);
        Block leftNeighbour = worldQueryService.getBlockAt(leftNeighbourLocation);
        blocks.add(leftNeighbour);

        BlockLocation rightNeighbourLocation = location.withY(currentY + 1);
        Block rightNeighbour = worldQueryService.getBlockAt(rightNeighbourLocation);
        blocks.add(rightNeighbour);

        return blocks;
    }

    @Override
    public Set<Block> getAdjacentBlocksZ(Block block) {
        Set<Block> blocks = new HashSet<>();
        BlockLocation location = block.location();
        int currentZ = location.z();

        BlockLocation leftNeighbourLocation = location.withZ(currentZ - 1);
        Block leftNeighbour = worldQueryService.getBlockAt(leftNeighbourLocation);
        blocks.add(leftNeighbour);

        BlockLocation rightNeighbourLocation = location.withZ(currentZ + 1);
        Block rightNeighbour = worldQueryService.getBlockAt(rightNeighbourLocation);
        blocks.add(rightNeighbour);

        return blocks;
    }

    @Override
    public Set<Block> getAdjacentBlocksXY(Block block) {
        Set<Block> adjacentBlocks = new HashSet<>();
        adjacentBlocks.addAll(getAdjacentBlocksX(block));
        adjacentBlocks.addAll(getAdjacentBlocksY(block));
        return adjacentBlocks;
    }

    @Override
    public Set<Block> getAdjacentBlocksXZ(Block block) {
        Set<Block> adjacentBlocks = new HashSet<>();
        adjacentBlocks.addAll(getAdjacentBlocksX(block));
        adjacentBlocks.addAll(getAdjacentBlocksZ(block));
        return adjacentBlocks;
    }

    @Override
    public Set<Block> getAdjacentBlocksYZ(Block block) {
        Set<Block> adjacentBlocks = new HashSet<>();
        adjacentBlocks.addAll(getAdjacentBlocksY(block));
        adjacentBlocks.addAll(getAdjacentBlocksZ(block));
        return adjacentBlocks;
    }

    @Override
    public Block getRelative(Block block, BlockFace at) {
        BlockLocation location = block.location();
        int currentX = location.x();
        int currentY = location.y();
        int currentZ = location.z();
        switch (at) {
            case NORTH:
                BlockLocation northLocation = location.withZ(currentZ - 1);
                return worldQueryService.getBlockAt(northLocation);
            case EAST:
                BlockLocation eastLocation = location.withX(currentX + 1);
                return worldQueryService.getBlockAt(eastLocation);
            case SOUTH:
                BlockLocation southLocation = location.withZ(currentZ + 1);
                return worldQueryService.getBlockAt(southLocation);
            case WEST:
                BlockLocation westLocation = location.withX(currentX - 1);
                return worldQueryService.getBlockAt(westLocation);
            case UP:
                BlockLocation upLocation = location.withY(currentY + 1);
                return worldQueryService.getBlockAt(upLocation);
            case DOWN:
                BlockLocation downLocation = location.withY(currentY - 1);
                return worldQueryService.getBlockAt(downLocation);
            default:
                throw new IllegalArgumentException("Failed to get relative block for illegal block face " + at);
        }
    }

    @Override
    public BoundingBlockBox getBoundingBox(Collection<Block> blocks) {
        if (blocks == null || blocks.isEmpty()) {
            return null;
        }

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxZ = Integer.MIN_VALUE;
        for (Block block : blocks) {
            BlockLocation location = block.location();
            if (location.x() < minX) {
                minX = location.x();
            }
            if (location.x() > maxX) {
                maxX = location.x();
            }
            if (location.y() < minY) {
                minY = location.y();
            }
            if (location.y() > maxY) {
                maxY = location.y();
            }
            if (location.z() < minZ) {
                minZ = location.z();
            }
            if (location.z() > maxZ) {
                maxZ = location.z();
            }
        }

        World world = blocks.iterator().next().location().world();
        Vector3i minimumCorner = new Vector3i(minX, minY, minZ);
        Vector3i maximumCorner = new Vector3i(maxX, maxY, maxZ);
        return boundingBlockBoxFactory.createBoundingBlockBox(world, minimumCorner, maximumCorner);
    }

    @Override
    public List<Block> getWithinRadius(Location location, float radius) {
        World world = location.world();
        Vector3i minimumCorner = location.subtract(radius, radius, radius)
                .toBlockLocation()
                .translation();
        Vector3i maximumCorner = location.add(1 + radius, 1 + radius, 1 + radius)
                .toBlockLocation()
                .translation();
        BoundingBlockBox box = boundingBlockBoxFactory.createBoundingBlockBox(world, minimumCorner, maximumCorner);
        Spheref sphere = new Spheref(location.x(), location.y(), location.z(), radius);
        return box.stream()
                .filter(block -> {
                    Location blockLocation = block.location().toLocation();
                    Vector3fc jomlMinCorner = new Vector3f(blockLocation.x(), blockLocation.y(), blockLocation.z());
                    AABBf blockBox = new AABBf(jomlMinCorner, jomlMinCorner.add(1, 1, 1, new Vector3f()));
                    return Intersectionf.testAabSphere(blockBox, sphere);
                })
                .collect(Collectors.toList());
    }

    @Override
    public BlockIntersection intersectBlock(Location origin, Location target, Predicate<Block> ignore) {
        BlockIterator blockIterator = blockIteratorFactory.createBlockIterator(origin, target);
        while (blockIterator.hasNext()) {
            Block currentBlock = blockIterator.next();
            if (!ignore.test(currentBlock)) {
                Location intersection = blockIterator.calculateIntersection();
                return new SimpleBlockIntersection(currentBlock, null, intersection);
            }
        }
        return null;
    }
}
