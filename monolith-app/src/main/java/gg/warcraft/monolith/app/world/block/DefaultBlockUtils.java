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
