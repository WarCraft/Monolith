package gg.warcraft.monolith.app.world.block;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.WorldService;
import gg.warcraft.monolith.api.block.Block;
import gg.warcraft.monolith.api.block.BlockIterator;

public class SimpleBlockIterator implements BlockIterator {
    private final WorldService worldService;
    private final Location origin;
    private final float maxDistance;
    private final Vector3f direction;

    private Location scanLocation;
    private float distance;
    private BlockLocation currentBlockLocation;
    private BlockLocation nextBlockLocation;

    @Inject
    public SimpleBlockIterator(WorldService worldService,
                               @Assisted("origin") Location origin, @Assisted("target") Location target) {
        this.worldService = worldService;
        this.origin = origin;
        this.maxDistance = origin.translation().distanceTo(target.translation());
        this.direction = target.subtract(origin).translation().normalize();
        this.scanLocation = origin;
        this.currentBlockLocation = null;
        this.nextBlockLocation = Location.toBlockLocation(origin);
    }

    BlockLocation calculateNext() {
        Vector3f delta;
        if (distance + 1 < maxDistance) {
            delta = direction;
            distance += 1;

            scanLocation = scanLocation.add(delta);
            if (Location.toBlockLocation(scanLocation).equals(currentBlockLocation)) {
                return calculateNext();
            }
            return Location.toBlockLocation(scanLocation);
        } else {
            delta = direction.multiply(maxDistance - distance);
            distance = maxDistance;

            scanLocation = scanLocation.add(delta);
            if (Location.toBlockLocation(scanLocation).equals(currentBlockLocation)) {
                return null;
            }
            return Location.toBlockLocation(scanLocation);
        }
    }

    @Override
    public Location calculateIntersection() {
        return BlockLocation.toLocation(currentBlockLocation).add(0.5f, 0.5f, 0.5f);
        // FIXME
//        System.out.println("DEBUG Calculating intersection for " + currentBlockLocation);
//        Vector3f blockMinimumCorner = currentBlockLocation.toLocation().toVector();
//        AABBf blockBoundingBox = new AABBf(blockMinimumCorner, blockMinimumCorner.add(1, 1, 1, new Vector3f()));
//        Rayf iteratorRay = new Rayf(origin.toVector(), direction);
//        Vector2f intersectionResult = new Vector2f();
//        if (blockBoundingBox.intersectRay(iteratorRay, intersectionResult)) {
//            float nearIntersectionScalar = intersectionResult.x;
//            return origin.add(direction.mul(nearIntersectionScalar, new Vector3f()));
//        } else {
//            throw new IllegalStateException("Failed to calculate intersection for current block location in block " +
//                    "iterator, this should not be possible.");
//        }
    }

    @Override
    public boolean hasNext() {
        return nextBlockLocation != null;
    }

    @Override
    public Block next() {
        currentBlockLocation = nextBlockLocation;
        nextBlockLocation = calculateNext();
        Block block = worldService.getBlock(currentBlockLocation);
        return block;
    }
}
