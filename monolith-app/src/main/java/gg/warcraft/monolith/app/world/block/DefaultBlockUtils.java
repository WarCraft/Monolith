package gg.warcraft.monolith.app.world.block;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.WorldService;
import gg.warcraft.monolith.api.block.*;
import gg.warcraft.monolith.api.block.box.BlockBox;
import gg.warcraft.monolith.api.block.box.BlockBoxReader;
import org.joml.*;
import scala.jdk.javaapi.CollectionConverters;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultBlockUtils implements BlockUtils {
    private final WorldService worldService;
    private final BlockIteratorFactory blockIteratorFactory;

    @Inject
    public DefaultBlockUtils(WorldService worldService, BlockIteratorFactory blockIteratorFactory) {
        this.worldService = worldService;
        this.blockIteratorFactory = blockIteratorFactory;
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
        Block leftNeighbour = worldService.getBlock(leftNeighbourLocation);
        blocks.add(leftNeighbour);

        BlockLocation rightNeighbourLocation = location.withX(currentX + 1);
        Block rightNeighbour = worldService.getBlock(rightNeighbourLocation);
        blocks.add(rightNeighbour);

        return blocks;
    }

    @Override
    public Set<Block> getAdjacentBlocksY(Block block) {
        Set<Block> blocks = new HashSet<>();
        BlockLocation location = block.location();
        int currentY = location.y();

        BlockLocation leftNeighbourLocation = location.withY(currentY - 1);
        Block leftNeighbour = worldService.getBlock(leftNeighbourLocation);
        blocks.add(leftNeighbour);

        BlockLocation rightNeighbourLocation = location.withY(currentY + 1);
        Block rightNeighbour = worldService.getBlock(rightNeighbourLocation);
        blocks.add(rightNeighbour);

        return blocks;
    }

    @Override
    public Set<Block> getAdjacentBlocksZ(Block block) {
        Set<Block> blocks = new HashSet<>();
        BlockLocation location = block.location();
        int currentZ = location.z();

        BlockLocation leftNeighbourLocation = location.withZ(currentZ - 1);
        Block leftNeighbour = worldService.getBlock(leftNeighbourLocation);
        blocks.add(leftNeighbour);

        BlockLocation rightNeighbourLocation = location.withZ(currentZ + 1);
        Block rightNeighbour = worldService.getBlock(rightNeighbourLocation);
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
                return worldService.getBlock(northLocation);
            case EAST:
                BlockLocation eastLocation = location.withX(currentX + 1);
                return worldService.getBlock(eastLocation);
            case SOUTH:
                BlockLocation southLocation = location.withZ(currentZ + 1);
                return worldService.getBlock(southLocation);
            case WEST:
                BlockLocation westLocation = location.withX(currentX - 1);
                return worldService.getBlock(westLocation);
            case UP:
                BlockLocation upLocation = location.withY(currentY + 1);
                return worldService.getBlock(upLocation);
            case DOWN:
                BlockLocation downLocation = location.withY(currentY - 1);
                return worldService.getBlock(downLocation);
            default:
                throw new IllegalArgumentException("Failed to get relative block for illegal block face " + at);
        }
    }

    @Override
    public BlockBox getBoundingBox(Collection<Block> blocks) {
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
        return new BlockBox(world, minimumCorner, maximumCorner);
    }

    @Override
    public List<Block> getWithinRadius(Location location, float radius) {
        World world = location.world();
        Vector3i minimumCorner =
                Location.toBlockLocation(location.subtract(radius, radius, radius))
                        .translation();
        Vector3i maximumCorner =
                Location.toBlockLocation(location.add(1 + radius, 1 + radius, 1 + radius))
                        .translation();
        BlockBox box = new BlockBox(world, minimumCorner, maximumCorner);
        Spheref sphere = new Spheref(location.x(), location.y(), location.z(), radius);
        BlockBoxReader reader = new BlockBoxReader(box, BlockDirection.NORTH, worldService);
        return CollectionConverters.asJava(reader.getBlocks()
                .filter(block -> {
                    Location blockLocation = BlockLocation.toLocation(block.location());
                    Vector3fc jomlMinCorner = new Vector3f(blockLocation.x(), blockLocation.y(), blockLocation.z());
                    AABBf blockBox = new AABBf(jomlMinCorner, jomlMinCorner.add(1, 1, 1, new Vector3f()));
                    return Intersectionf.testAabSphere(blockBox, sphere);
                }));
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
